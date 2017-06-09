package damiano.printer

import groovy.transform.PackageScope
import org.springframework.core.io.Resource

@PackageScope
class ImageFromResource implements Image {

	Resource imgResource

	ImageFromResource(Resource resource) {
		this.imgResource = resource
	}

	@Override
	byte[] toByteArray() {
		return imgResource.inputStream.bytes
	}
}
