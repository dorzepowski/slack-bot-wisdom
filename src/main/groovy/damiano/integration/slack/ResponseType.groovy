package damiano.integration.slack

import com.fasterxml.jackson.annotation.JsonValue

enum ResponseType {

	IN_CHANEL("in_channel")

	@JsonValue
	private final String value

	ResponseType(String value) {
		this.value = value
	}
}