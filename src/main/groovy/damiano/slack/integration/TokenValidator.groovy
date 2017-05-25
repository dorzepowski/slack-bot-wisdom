package damiano.slack.integration

import groovy.transform.PackageScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@PackageScope
@Service
class TokenValidator {

	final String token

	TokenValidator(@Value('${slack.token}') String token) {
		this.token = token
	}

	void validate(String tokenToValidate) {
		println tokenToValidate
		println token == tokenToValidate
		assert token == tokenToValidate
	}
}
