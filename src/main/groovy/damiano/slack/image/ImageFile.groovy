package damiano.slack.image

import javax.imageio.ImageIO

import groovy.transform.PackageScope
import org.springframework.core.io.Resource

@PackageScope
class ImageFile {

	private final Resource imageResource

	ImageFile(Resource imageResource) {
		this.imageResource = imageResource
	}

	Image image() {
		return new ImageCopy(ImageIO.read(imageResource.inputStream))
	}


}
