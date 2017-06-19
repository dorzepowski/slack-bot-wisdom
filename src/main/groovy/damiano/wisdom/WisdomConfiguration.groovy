package damiano.wisdom

import damiano.Profiles
import groovy.transform.PackageScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@PackageScope
class WisdomConfiguration {


	@Profile(Profiles.FILE)
	@Bean
	WordsOfWisdom fileWordsOfWisdom() {
		File storageFile = new File("db.json")
		return new FileSystemWordsOfWisdomRepository(localWordsOfWisdom(), storageFile)
	}

	@Profile(Profiles.LOCAL)
	@Bean
	WordsOfWisdom localWordsOfWisdom() {
		return new LocalWordsOfWisdomRepository()
	}

}
