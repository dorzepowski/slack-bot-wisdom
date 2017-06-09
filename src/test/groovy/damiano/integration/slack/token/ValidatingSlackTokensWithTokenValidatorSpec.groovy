package damiano.integration.slack.token

import spock.lang.Specification

class ValidatingSlackTokensWithTokenValidatorSpec extends Specification {

	String multipleTokens = "1,AGdjr2"

	String wrongToken = "WRONG"

	def "accept one of registered token"() {
		given:
			def validator = new TokenValidator(multipleTokens)
			def tokenToValidate = "1"
		when:
			validator.validate(tokenToValidate)
		then:
			notThrown(InvalidToken)
	}

	def "do not accept token that isn't one of registered token"() {
		given:
			def validator = new TokenValidator(multipleTokens)
		when:
			validator.validate(wrongToken)
		then:
			thrown(InvalidToken)
	}

}
