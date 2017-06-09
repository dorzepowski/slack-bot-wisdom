package damiano.integration.slack.token

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Slf4j
class TokenValidator {

	private static final String DELIMITER = ","

	private final List<String> tokens

	TokenValidator(@Value('${SLACK_TOKENS}') String token) {
		tokens = token.split(DELIMITER)
		log.debug("Validator configured with tokens = [$tokens]")
	}

	void validate(String tokenToValidate) {
		log.debug("validating token = [$tokenToValidate]")
		if (!tokens.contains(tokenToValidate)) {
			throw new InvalidToken()
		}
	}
}
