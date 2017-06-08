package damiano.printer

import java.awt.*

import groovy.transform.PackageScope

import static damiano.printer.Decoration.ITALIC
import static damiano.printer.Decoration.NONE

@PackageScope
class TextLine implements Printable {

	private final String text

	private int size = 12

	private Color color = Color.BLACK

	private Decoration decoration = NONE


	TextLine(String text, int size) {
		this.text = text
		this.size = size
	}

	@Override
	Printable withColor(Color color) {
		this.color = color
		return this
	}

	@Override
	Printable italic() {
		this.decoration = ITALIC
		return this
	}

	@Override
	void printOn(Media media) {
		media.text(text).withColor(color).withSize(size).withDecoration(decoration).print()
	}
}
