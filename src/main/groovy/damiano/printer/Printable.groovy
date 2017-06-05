package damiano.printer

import java.awt.*

interface Printable {


	Printable withColor(Color color)

	Printable italic()

	void printOn(Graphics2D g)
}