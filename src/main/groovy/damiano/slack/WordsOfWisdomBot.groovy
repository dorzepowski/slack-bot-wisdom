package damiano.slack

import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@TypeChecked
@SpringBootApplication
class WordsOfWisdomBot {


	static void main(String[] args) {
		SpringApplication.run(WordsOfWisdomBot.class, args)
	}

/*	@Bean
	CommandLineRunner demo(WiseManImage image) {
		return (CommandLineRunner) { args ->
			def wisdom = new Wisdom("One there should be, no more, no less")
			def wisdomImage = image.with(wisdom)
			wisdomImage.write(new File("test1.png"))
			println "created file test1.png"

			def wisdom2 = new Wisdom("Woooooohooooooo")
			def wisdomImage2 = image.with(wisdom2)
			wisdomImage2.write(new File("test2.png"))
			println "created file test2.png"
		}

	}*/

}

