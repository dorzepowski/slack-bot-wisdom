package damiano.printer

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import groovy.transform.PackageScope

class Image {

	private final BufferedImage image

	@PackageScope
	Image(BufferedImage image) {
		this.image = image
	}

	byte[] toByteArray() {
		ByteArrayOutputStream os = new ByteArrayOutputStream()
		ImageIO.write(image, "png", os)
		return os.toByteArray()
	}

}
