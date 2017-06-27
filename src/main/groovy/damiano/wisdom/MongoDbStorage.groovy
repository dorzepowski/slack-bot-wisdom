package damiano.wisdom

import damiano.Profiles
import groovy.transform.PackageScope
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.MongoRepository

@PackageScope
@Profile(Profiles.MONGO_DB)
interface MongoDbStorage extends MongoRepository<Quote, String>, Storage {

}