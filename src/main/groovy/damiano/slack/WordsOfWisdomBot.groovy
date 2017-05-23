package damiano.slack

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
	CommandLineRunner demo() {
		return  {



		}


	}
}
