package damiano.integration.slack

import javax.servlet.http.HttpServletRequest

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.PackageScope

@PackageScope
class SlackResponse {

	@JsonProperty("response_type")
	private final ResponseType responseType = ResponseType.IN_CHANEL

	@JsonProperty
	private final List<Attachment> attachments = []

	@JsonIgnore
	private final HttpServletRequest request

	SlackResponse(HttpServletRequest request, String imageId) {
		this.request = request
		attachments.add(new Attachment(imageId))
	}

	private class Attachment {

		@JsonProperty("image_url")
		private final String imageUrl

		Attachment(String imageId) {
			URL url = request.requestURL.toURL()
			this.imageUrl = url.getProtocol() + "://" + url.getAuthority() + "/wisdom/$imageId"
		}
	}
}
