package damiano.printer

import groovy.transform.PackageScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
@PackageScope
class PrinterConfiguration {

	@Bean
	BackgroundImage backgroundImage(Image image) {
		return new Background(image)
	}

	@Bean
	Image image(
			@Value(value = "classpath:dc-background.png") Resource resource,
			@Value(value = '${BG_KEY}') String encryptionKey
	) {
		return new Decrypted(new ImageFromResource(resource), encryptionKey)
	}
}
