package damiano.printer

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import groovy.transform.PackageScope

@PackageScope
class ImageFromBufferedImage implements Image {

	private final BufferedImage image

	ImageFromBufferedImage(BufferedImage image) {
		this.image = image
	}

	@Override
	byte[] toByteArray() {
		ByteArrayOutputStream os = new ByteArrayOutputStream()
		ImageIO.write(image, "png", os)
		return os.toByteArray()
	}

}
