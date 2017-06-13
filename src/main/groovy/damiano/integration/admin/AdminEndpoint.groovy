package damiano.integration.admin

import javax.servlet.http.HttpServletRequest

import damiano.admin.AdminFacade
import groovy.transform.TypeChecked
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@TypeChecked
@RestController
@RequestMapping("admin")
class AdminEndpoint {

	private final AdminFacade adminFacade

	AdminEndpoint(AdminFacade adminFacade) {
		this.adminFacade = adminFacade
	}

	@PostMapping("wisdom")
	ResponseEntity<Void> feed(@RequestBody List<String> wordsToStore, HttpServletRequest request) {
		adminFacade.store(wordsToStore)
		return ResponseEntity.created(request.requestURL.toURI()).build()
	}

	@GetMapping("wisdom")
	ResponseEntity<List<String>> listAll() {
		return ResponseEntity.ok(adminFacade.listAllWisdomTexts())
	}

}
