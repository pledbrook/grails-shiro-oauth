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
class ShiroOauthGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "1.3.3 > *"
    def dependsOn = [shiro: "1.0 > *", oauthScribe: "1.3 > *"]

    def title = "Shiro OAuth Plugin"
    def author = "Peter Ledbrook"
    def authorEmail = "p.ledbrook@cacoethes.co.uk"
    def description = """\
Adds OAuth-based authentication to the [Shiro plugin](http://grails.org/plugin/shiro) using the [Scribe OAuth plugin](http://grails.org/plugin/oauth-scribe). This plugin provides an OAuth realm that can easily be integrated into existing applications and a host of utility functions to make things like "log in with Twitter" almost trivial.
"""

    def documentation = "http://pledbrook.github.com/grails-shiro-oauth/"
    def license = "APACHE"
    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPSHIRO" ]
    def scm = [ url: "https://github.com/pledbrook/grails-shiro-oauth/" ]
}
