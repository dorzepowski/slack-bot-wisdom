package damiano.wisdom

import java.security.MessageDigest

import groovy.transform.PackageScope

@PackageScope
class WordOfWisdom {

	private final String wisdom

	private String key

	WordOfWisdom(String wisdom) {
		this.wisdom = wisdom
	}

	String getId() {
		return key()
	}

	@PackageScope
	String key() {
		if (!key) {
			key = generateKey()
		}
		return key
	}

	private String generateKey() {
		MessageDigest.getInstance('SHA-256')
				.digest(wisdom.bytes)
				.encodeHex()
				.toString()
	}
}
