package damiano.wisdom.printing

import damiano.printer.Media
import groovy.transform.PackageScope

@PackageScope
trait DoNothingMedia implements Media {


	@Override
	void moveRight(int offset) {

	}

	@Override
	void scroll(int by) {

	}

	@Override
	void scrollToNextLine() {

	}

	@Override
	void dispose() {

	}
}
