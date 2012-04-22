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

/**
 * Shiro authentication token for Facebook users. It's a standard {@link OAuthToken}
 * that returns the Facebook ID as the principal.
 */
class FacebookOAuthToken extends OAuthToken {
    static final String PROVIDER_NAME = "facebook"

    def username

    FacebookOAuthToken(scribeToken, username) {
        super(scribeToken)
        this.username = username
    }

    /**
     * Returns the Facebook ID for the associated user.
     */
    def getPrincipal() {
        return username
    }

    String getProviderName() {
        return PROVIDER_NAME
    }
}
