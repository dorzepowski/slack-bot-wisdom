package damiano.printer

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import groovy.transform.PackageScope

import static java.nio.charset.StandardCharsets.UTF_8
import static javax.crypto.Cipher.DECRYPT_MODE

@PackageScope
class Decrypted implements Image {

	public static final String ALGORITHM = "AES"

	private final byte[] image

	Decrypted(Image image, String password) {
		this.image = decrypt(image, password)
	}

	byte[] decrypt(Image image, String password) {
		def cipher = Cipher.getInstance(ALGORITHM)
		def secretKey = new SecretKeySpec(password.getBytes(UTF_8), ALGORITHM)
		cipher.init(DECRYPT_MODE, secretKey)
		cipher.doFinal(image.toByteArray())
	}

	@Override
	byte[] toByteArray() {
		image
	}
}
