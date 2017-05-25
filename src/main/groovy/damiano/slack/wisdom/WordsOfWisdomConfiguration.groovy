package damiano.slack.wisdom

import groovy.transform.PackageScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@PackageScope
@Configuration
class WordsOfWisdomConfiguration {

	@Bean
	WordsOfWisdomFacade facade() {
		return new WordsOfWisdomFacade()
	}
}

