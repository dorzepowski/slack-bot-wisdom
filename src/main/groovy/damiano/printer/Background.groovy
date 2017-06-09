package damiano.printer

import javax.imageio.ImageIO

import groovy.transform.PackageScope

@PackageScope
class Background implements BackgroundImage {

	private final Image image

	Background(Image image) {
		this.image = image
	}

	@Override
	Printer newPrinter() {
		return new BufferedImagePrinter(ImageIO.read(new ByteArrayInputStream(image.toByteArray())))
	}

}
