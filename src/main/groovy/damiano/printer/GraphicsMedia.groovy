package damiano.printer

import java.awt.*

import groovy.transform.PackageScope

@PackageScope
class GraphicsMedia implements Media {

	private final Graphics2D graphics

	private PrinterHead head = new PrinterHead()

	GraphicsMedia(Graphics2D graphics) {
		this.graphics = graphics
		graphics.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}

	@Override
	Media.Text text(String text) {
		return new TextLine(text)
	}

	@Override
	void moveRight(int offset) {
		head = head >> offset
	}

	@Override
	void scroll(int scrollBy) {
		head = head + scrollBy
	}

	@Override
	void scrollToNextLine() {
		scroll(graphics.fontMetrics.height)
	}

	@Override
	void dispose() {
		graphics.dispose()
	}

	private class TextLine implements Media.Text {

		final String text

		Color color

		int size

		Decoration decoration

		TextLine(String text) {
			this.text = text
		}

		@Override
		Media.Text withColor(Color color) {
			this.color = color
			return this
		}

		@Override
		Media.Text withSize(int size) {
			this.size = size
			return this
		}

		@Override
		Media.Text withDecoration(Decoration decoration) {
			this.decoration = decoration
			return this
		}

		@Override
		void print() {
			graphics.color = color
			graphics.font = new Font(Font.SERIF, decoration.toStyleInt(), size)
			graphics.drawString(text, head.x, head.y)
		}

	}


	private static class PrinterHead {

		final int x

		final int y

		PrinterHead() {
			this.x = 0
			this.y = 0
		}

		PrinterHead(int x, int y) {
			this.x = x
			this.y = y
		}

		PrinterHead plus(int deltaY) {
			return new PrinterHead(this.x, this.y + deltaY)
		}

		PrinterHead minus(int deltaY) {
			return new PrinterHead(this.x, this.y - deltaY)
		}

		PrinterHead leftShift(int deltaX) {
			return new PrinterHead(this.x - deltaX, this.y)
		}

		PrinterHead rightShift(int deltaX) {
			return new PrinterHead(this.x + deltaX, this.y)
		}
	}
}
