package damiano.slack.integration

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.PackageScope

@PackageScope
class SlackResponse {

	@JsonProperty("response_type")
	private ResponseType responseType = ResponseType.IN_CHANEL

	@JsonProperty
	private String text

	SlackResponse(String text) {
		this.text = text
	}
}
