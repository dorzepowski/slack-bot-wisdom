package damiano.printer

import java.awt.*
import java.awt.image.BufferedImage
import java.util.List

import damiano.printer.Image
import groovy.transform.PackageScope

class Printer {

	private static final int MARGIN_LEFT = 50

	private static final int MARGIN_TOP = 100

	private final BufferedImage image

	private final List<Printable> lines = []


	@PackageScope
	Printer(BufferedImage image) {
		this.image = image
	}


	void println(Closure<Printable> printableSupplier) {
		printableSupplier.resolveStrategy = Closure.TO_SELF
		Printable line = use(PrintableStringApi, printableSupplier)
		lines.add(line)
	}


	Image toImage() {
		Graphics2D g = createGraphics()

		int lastLineStart = printLines(g)

		cleanup(g, lastLineStart)

		return new Image(image)
	}

	private Graphics2D createGraphics() {
		def g = image.createGraphics()
		g.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
		return g
	}

	private int printLines(Graphics2D g) {
		int lastLineStart = MARGIN_TOP
		g.translate(MARGIN_LEFT, MARGIN_TOP)
		for (line in lines) {
			g.translate(0, g.fontMetrics.height)
			line.printOn(g)
			lastLineStart += g.fontMetrics.height
		}
		return lastLineStart
	}

	private void cleanup(Graphics2D g, int newLineStart) {
		g.translate(-MARGIN_LEFT, -newLineStart)
		g.dispose()
	}


}
