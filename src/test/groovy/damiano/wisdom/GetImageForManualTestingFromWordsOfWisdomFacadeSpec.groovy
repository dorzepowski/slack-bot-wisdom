package damiano.wisdom

import damiano.printer.Image
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

import static org.mockito.BDDMockito.given
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@ContextConfiguration
@SpringBootTest(webEnvironment = NONE)
class GetImageForManualTestingFromWordsOfWisdomFacadeSpec extends Specification {

	@MockBean
	Image image

	@Shared
	static Resource imgResource = new ClassPathResource("dc-background.png")

	@Autowired
	WordsOfWisdomFacade wordsOfWisdomFacade

	void setup() {
		given(image.toByteArray()).willReturn(imgResource.inputStream.bytes)
	}

	@Ignore("for manual testing of generated image")
	def "save image for manual test"() {
		given:
			String text = """
							some wise text
							in
							multiline
						""".stripIndent()
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