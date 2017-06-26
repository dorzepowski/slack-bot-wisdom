package damiano

import groovy.transform.CompileStatic

@CompileStatic
class Profiles {

	static final String LOCAL = 'local'

	static final String FILE = 'file'

	static final String MONGO_DB = 'mongo'

	static final String NON_MONGO_DB = '!mongo'
}
