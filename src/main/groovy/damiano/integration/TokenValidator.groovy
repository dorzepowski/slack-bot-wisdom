package damiano.integration

import groovy.transform.PackageScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@PackageScope
@Service
class TokenValidator {

	final String token

	TokenValidator(@Value('${SLACK_TOKEN}') String token) {
		this.token = token
		println "token = $token"
	}

	void validate(String tokenToValidate) {
		println tokenToValidate
		println token == tokenToValidate
		if (token != tokenToValidate) {
			throw new InvalidToken()
		}
	}
}
