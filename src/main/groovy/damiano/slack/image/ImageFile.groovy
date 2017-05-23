package damiano.slack.image

import javax.imageio.ImageIO

import groovy.transform.PackageScope

@PackageScope
class ImageFile {


	private final InputStream imageStream

	ImageFile(InputStream imageStream) {
		this.imageStream = imageStream
	}

	Image image() {
		return new ImageCopy(ImageIO.read(imageStream))
	}


}
