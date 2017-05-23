package damiano.slack

import damiano.slack.image.WiseManImage
import damiano.slack.wisdom.Wisdom
import groovy.transform.TypeChecked
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@TypeChecked
@SpringBootApplication
class WordsOfWisdomBot {


	static void main(String[] args) {
		SpringApplication.run(WordsOfWisdomBot.class, args)
	}

	@Bean
	CommandLineRunner demo(WiseManImage image) {
		return (CommandLineRunner) { args ->
			def wisdom = new Wisdom("One there should be, no more, no less")
			def wisdomImage = image.with(wisdom)
			wisdomImage.write(new File("test1.png"))
			println "created file test1.png"
		}

	}

}

