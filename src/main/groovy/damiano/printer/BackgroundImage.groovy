package damiano.printer

import javax.imageio.ImageIO

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class BackgroundImage {

	private final Resource resource

	BackgroundImage(@Value(value = "classpath:DC.png") Resource resource) {
		this.resource = resource
	}

	Printer newPrinter() {
		return new BufferedImagePrinter(ImageIO.read(resource.inputStream))
	}
}
