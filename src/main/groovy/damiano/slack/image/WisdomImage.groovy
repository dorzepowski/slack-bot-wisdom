package damiano.slack.image

import damiano.slack.wisdom.Wisdom
import groovy.transform.PackageScope


class WisdomImage {

	private boolean enhanced = false;
	private final Image image
	private final Wisdom wisdom
	private final Author author

	@PackageScope
	WisdomImage(Image image, Wisdom wisdom, Author author) {
		this.image = image
		this.wisdom = wisdom
		this.author = author
	}

	void write(File file) {
		enhanceImage()
		image.writeTo(file)
	}

	private void enhanceImage() {
		if(!enhanced){
			wisdom.enhance(image)
			author.enhance(image)
			enhanced = true
		}
	}
}
