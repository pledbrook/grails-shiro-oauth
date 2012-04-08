/*
 * Copyright 2012 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.shiro.oauth

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import static uk.co.desirableobjects.oauth.scribe.OauthService.ACCESS_TOKEN_SESSION_KEY as TOKEN_KEY

/**
 * Simple helper controller for handling OAuth authentication and integrating it
 * into Shiro.
 */
class ShiroOAuthController {
    def grailsApplication

    /**
     * This can be used as a callback for a successful OAuth authentication
     * attempt. It logs the associated user in if he or she has an internal
     * Shiro account and redirects to <tt>targetUri</tt> (provided as a URL
     * parameter or in the session). Otherwise it redirects to a URL for
     * linking OAuth identities to Shiro accounts. The application must implement
     * the page and provide the associated URL via the
     * <tt>security.shio.oauth.linkAccountUrl</tt> configuration setting.
     */
    def onSuccess() {
        // Validate the 'provider' URL. Any errors here are either misconfiguration
        // or web crawlers (or malicious users).
        def oauthConfig = grailsApplication.config.security.shiro.oauth
        if (!params.provider) {
            renderError 400, "The Shiro OAuth callback URL must include the 'provider' URL parameter."
            return
        }

        if (!oauthConfig.providers.containsKey(params.provider)) {
            renderError 400, "Unrecognised provider '${params.provider}'"
            return
        }

        if (!session[TOKEN_KEY]) {
            renderError 500, "No OAuth token in the session!"
            return
        }

        // Create the relevant authentication token and attempt to log in.
        def authToken = getProviderClass(oauthConfig, params.provider).newInstance(session[TOKEN_KEY])
        def targetUri = params.targetUri ?: session.targetUri

        try {
            SecurityUtils.subject.login authToken

            // Login successful, so don't need targetUri in session any more.
            session.removeAttribute "targetUri"
            redirect uri: targetUri
        }
        catch (AuthenticationException ex) {
            // This OAuth account hasn't been registered against an internal
            // account yet. Give the user the opportunity to create a new
            // internal account or link to an existing one.
            session.shiroAuthToken = authToken

            def redirectUrl = oauthConfig.linkAccountUrl
            log.debug "Redirecting to link account URL: ${redirectUrl}"
            redirect(redirectUrl instanceof Map ? redirectUrl : [uri: redirectUrl])
        }
    }

    protected renderError(code, msg) {
        log.error msg + " (returning $code)"
        render status: code, text: msg
    }

    protected getProviderClass(oauthConfig, providerName) {
        return getClass().classLoader.loadClass(oauthConfig.providers."${providerName}")
    }
}

