package damiano

import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@TypeChecked
@SpringBootApplication
class WordsOfWisdomBot {


	static void main(String[] args) {

		SpringApplication.run(WordsOfWisdomBot.class, args)
	}

}

