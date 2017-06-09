package damiano.wisdom

import javax.imageio.ImageIO

import damiano.printer.BackgroundImage
import damiano.printer.BufferedImagePrinter
import damiano.printer.Image
import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class GetImageForWisdomFromWordsOfWisdomFacadeTest extends Specification {

	def "provide id for wisdom text"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
		then:
			id
	}

	def "get image for received id"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id).toByteArray()
		then:
			image
			image.length
	}

	//@Ignore("for manual testing of generated image")
	def "save image for manual test"() {
		given:
			String text = """
							some wise text
							in
							multiline
						""".stripIndent()
			WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()

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

	private WordsOfWisdomFacade WordsOfWisdomFacade() {
		new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), (BackgroundImage) {
			new BufferedImagePrinter(ImageIO.read(new ClassPathResource("dc-background.png").inputStream))
		})
	}

	private String generateImageName() {
		"test" + UUID.randomUUID().toString() + ".png"
	}


}
