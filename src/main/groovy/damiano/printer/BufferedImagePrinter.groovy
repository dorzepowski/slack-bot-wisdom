package damiano.printer

import java.awt.image.BufferedImage

import groovy.transform.PackageScope

@PackageScope
class BufferedImagePrinter implements Printer {

	private static final int MARGIN_LEFT = 50

	private static final int MARGIN_TOP = 50

	private final BufferedImage image

	private final List<Printable> lines = []


	@PackageScope
	BufferedImagePrinter(BufferedImage image) {
		this.image = image
	}


	@Override
	void println(Closure<Printable> printableSupplier) {
		printableSupplier.resolveStrategy = Closure.TO_SELF
		Printable line = use(PrintableStringApi, printableSupplier)
		lines.add(line)
	}


	@Override
	Image toImage() {
		Media media = new GraphicsMedia(image.createGraphics())

		printOn(media)

		return new Image(image)
	}

	private void printOn(Media media) {
		setMarginsOn(media)
		printLinesOn(media)
		media.dispose()
	}

	private void setMarginsOn(Media media) {
		media.moveRight(MARGIN_LEFT)
		media.scroll(MARGIN_TOP)
	}

	private void printLinesOn(Media media) {
		for (line in lines) {
			media.scrollToNextLine()
			line.printOn(media)
		}
	}
}
