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

import org.apache.shiro.authc.UnknownAccountException

/**
 * This is a Shiro realm that handles authentication via OAuth providers. The
 * actual authentication has to happen outside of the realm and an {@link OAuthToken}
 * created, but then this realm checks whether an internal Shiro account exists
 * for the OAuth identity. If there is one, both the ID of the user account and
 * the OAuth username are returned as principals (in that order). Otherwise an
 * <tt>UnknownAccountException</tt> is thrown, which generally means a Shiro
 * account needs linking to the OAuth identity.
 */
class OAuthRealm {
    static final authTokenClass = OAuthToken

    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.principal} with ${authToken.providerName} provider..."

        // Is there a record for the given user and provider? If so, return the
        // associated information. Otherwise, throw the 
        def identity = findIdentity(authToken.principal, authToken.providerName)
        if (!identity) {
            throw new UnknownAccountException("No account found for user [${authToken.principal}]")
        }

        return [identity.userId, identity.username]
    }

    protected findIdentity(principal, provider) {
        return ShiroOAuthIdentity.findByUsernameAndProvider(principal, provider)
    }
}
