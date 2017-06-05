package damiano.printer

import java.awt.*

import groovy.transform.PackageScope

@PackageScope
class TextLine implements Printable {

	private final String text

	private int size = 12

	private Color color = Color.BLACK

	private int style = Font.PLAIN


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
		this.style = Font.ITALIC
		return this
	}

	@Override
	void printOn(Graphics2D g) {
		g.color = color
		g.font = new Font(Font.SERIF, style, size)
		g.drawString(text, 0, 0)
	}
}
