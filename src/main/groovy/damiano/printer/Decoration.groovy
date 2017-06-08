package damiano.printer

import java.awt.*

import groovy.transform.PackageScope

@PackageScope
enum Decoration {

	NONE(Font.PLAIN),
	ITALIC(Font.ITALIC)

	private final int style

	Decoration(int style) {
		this.style = style
	}

	int toStyleInt() {
		return this.style
	}
}