package damiano.slack.wisdom


class WordsOfWisdomFacade {

	private Map<String, String> DB = ['1': "TODO or not TODO", "2": "One there should be. No more, no less."]

	String imageNameFor(String wisdom) {
		if (wisdom) {
			return "3.png"
		} else {
			return "1.png"
		}
	}
}
