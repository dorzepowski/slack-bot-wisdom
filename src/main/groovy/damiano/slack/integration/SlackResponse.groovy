package damiano.slack.integration

import com.fasterxml.jackson.annotation.JsonProperty
import damiano.wisdom.WordOfWisdom
import groovy.transform.PackageScope

@PackageScope
class SlackResponse {

	@JsonProperty("response_type")
	private ResponseType responseType = ResponseType.IN_CHANEL

	@JsonProperty
	private String text

	@JsonProperty
	private List<Attachment> attachments = []

	SlackResponse(String text) {
		this.text = text
	}

	SlackResponse(WordOfWisdom wordOfWisdom) {
		this.text = "Don't know yet what to do with words of wisdom"
	}


	private class Attachment {

		@JsonProperty("image_url")
		String imageUrl
	}
}
