package damiano.wisdom

import damiano.Profiles
import groovy.transform.PackageScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@PackageScope
class WisdomConfiguration {


	@Profile(Profiles.FILE)
	@Bean
	WordsOfWisdom fileWordsOfWisdom() {
		File storageFile = new File("db.json")
		storageFile.createNewFile()
		return new FileSystemWordsOfWisdomRepository(new LocalWordsOfWisdomRepository(), storageFile)
	}

	@Profile(Profiles.LOCAL)
	@Bean
	WordsOfWisdom localWordsOfWisdom() {
		return new LocalWordsOfWisdomRepository()
	}


	@Profile(Profiles.MONGO_DB)
	@Configuration
	@EnableMongoRepositories("damiano.wisdom")
	class MongoWisdomConfiguration {

	}

}
