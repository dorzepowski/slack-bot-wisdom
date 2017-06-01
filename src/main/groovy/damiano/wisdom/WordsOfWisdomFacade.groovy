package damiano.wisdom

import java.awt.*
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap
import javax.imageio.ImageIO

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Slf4j
@Service
class WordsOfWisdomFacade {

	@Value(value = "classpath:DC.png")
	private Resource image

	private final Map<String, String> DB = new ConcurrentHashMap()

	WordsOfWisdomFacade(@Value(value = "classpath:DC.png") Resource image) {
		this.image = image
	}

	String wisdomIdFor(String text) {
		String id = MessageDigest.getInstance('SHA-256').digest(text.bytes).encodeHex().toString()
		log.info("Wisdom to process: $text")
		DB.put(id, text)
		return id
	}

	byte[] wisdomImageBytesForId(String id) {
		String wisdom = DB.get(id) ?: throwTheyFucked()

		log.info "Read original image"
		def png = ImageIO.read(image.inputStream)
		def g = png.createGraphics()

		def y = 100

		wisdom.eachLine {
			log.info "Write wisdom line"
			g.color = Color.GRAY
			g.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
			g.font = new Font(Font.SERIF, Font.PLAIN, 30)
			g.drawString(it, 50, y)

			y += g.fontMetrics.height

		}

		log.info "Write author"
		g.font = new Font(Font.SERIF, Font.ITALIC, 30)
		g.drawString("~ Damiano Cohello", 50, y)
		g.dispose()

		log.info "Write image"
		ByteArrayOutputStream os = new ByteArrayOutputStream()
		ImageIO.write(png, "png", os)
		return os.toByteArray()
	}

	private static def throwTheyFucked() {
		throw new NotFound()
	}
}
