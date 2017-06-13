package damiano.admin

import damiano.wisdom.WordsOfWisdom
import groovy.transform.PackageScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@PackageScope
@Configuration
class AdminConfiguration {

	@Bean
	AdminFacade adminFacade(WordsOfWisdom wordsOfWisdom) {
		return new AdminFacade(wordsOfWisdom)
	}
}
