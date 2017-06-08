package damiano.printer

import java.awt.*

interface Media {

	Text text(String text)

	void moveRight(int offset)

	void scroll(int by)

	void scrollToNextLine()

	void dispose()

	interface Text {

		Text withColor(Color color)

		Text withSize(int size)

		Text withDecoration(Decoration decoration)

		void print()
	}
}
