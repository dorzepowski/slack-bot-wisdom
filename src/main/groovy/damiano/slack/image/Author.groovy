package damiano.slack.image

enum Author {

	DAMIANO_COHELLO("Damiano Cohello")

	private final String name

	Author(String name) {
		this.name = name
	}

	void enhance(Image image) {
		image.text("~ $name").italic().add()
	}
}