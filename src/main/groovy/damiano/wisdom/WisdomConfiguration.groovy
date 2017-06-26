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
	Storage fileStorage() {
		File storageFile = new File("db.json")
		storageFile.createNewFile()
		return new FileSystemStorage(storageFile)
	}


	@Profile(Profiles.NON_LOCAL)
	@Bean
	WordsOfWisdom persistedStorage(Storage storage) {
		return new PersistedWordsOfWisdomRepository(new LocalWordsOfWisdomRepository(), storage)
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
