package grails.plugin.shiro.oauth

import spock.lang.*

class OAuthTokenSpec extends Specification {
    def "Parameter extraction produces the correct map"() {
        when: "A test OAuth token is created"
        def token = new TestOAuthToken([rawResponse: inputData])

        then: "The token map to contain the entries provided by the inputData string"
        token.parameters == expected

        where:
        inputData             |        expected
        null                  |          [:]
        ""                    |          [:]
        "one=1"               |       [one: "1"]
        "one=1&two=2&ten=10"  | [one: "1", two: "2", ten: "10"]
    }
}

class TestOAuthToken extends OAuthToken {
    TestOAuthToken(token) { super(token) }
    String getPrincipal() { return "test" }
    String getProviderName() { return "unit tester" }
}
