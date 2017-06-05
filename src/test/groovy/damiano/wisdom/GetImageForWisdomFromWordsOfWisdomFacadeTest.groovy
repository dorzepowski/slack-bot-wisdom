package damiano.wisdom

import damiano.printer.Image
import damiano.printer.Media
import org.springframework.core.io.ClassPathResource
import spock.lang.Ignore
import spock.lang.Specification

class GetImageForWisdomFromWordsOfWisdomFacadeTest extends Specification {

	def "provide id for wisdom text"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), new Media(new ClassPathResource("DC.png")))
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
		then:
			id
	}

	def "get image for received id"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), new Media(new ClassPathResource("DC.png")))
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id).toByteArray()
		then:
			image
			image.length
	}

	@Ignore("for manual testing of generated image")
	def "save image for manual test"() {
		given:
			String text = """
							some wise text
							in
							multiline
						""".stripIndent()
			WordsOfWisdomFacade wordsOfWisdomFacade = new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), new Media(new ClassPathResource("DC.png")))

		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			def image = wordsOfWisdomFacade.wisdomImageBytesForId(id)
			saveAsImageFile(image)
		then:
			notThrown(Exception)
	}

	private void saveAsImageFile(Image image) {
		def file = new File(generateImageName())
		println "Generating image = $file.absolutePath"

		def fos = new FileOutputStream(file)
		fos.write(image.toByteArray())
		fos.close()
	}

	private String generateImageName() {
		"test" + UUID.randomUUID().toString() + ".png"
	}

}
