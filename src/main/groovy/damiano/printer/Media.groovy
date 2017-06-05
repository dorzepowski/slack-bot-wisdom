package damiano.printer

import javax.imageio.ImageIO

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class Media {

	private final Resource resource

	Media(@Value(value = "classpath:DC.png") Resource resource) {
		this.resource = resource
	}

	Printer newPrinter() {
		return new Printer(ImageIO.read(resource.inputStream))
	}
}
