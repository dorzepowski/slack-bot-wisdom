package damiano.slack.image

import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
@TypeChecked
@PackageScope
class WiseManFactory {

	@Bean
	WiseManImage wisdomImage(@Value(value = "classpath:DC.png") Resource image){
		def imageFile = new ImageFile(image)
		return new DamianoCohelloImage(imageFile)
	}

}
