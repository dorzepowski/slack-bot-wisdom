package damiano.slack.image

interface Image {

	ImageText text(String text)

	void writeTo(File file)
}