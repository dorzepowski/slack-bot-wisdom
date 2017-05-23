package damiano.slack.wisdom

import damiano.slack.image.Image
import groovy.transform.TypeChecked

@TypeChecked
class Wisdom {
	private final String text

	Wisdom(String text) {
		this.text = text
	}

	void enhance(Image image){
		image.text(text).add()
	}
}
