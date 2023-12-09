package ca.sheridancollege.banwsukh.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@GetMapping("/")
	public ResponseEntity<String> index() {
		logger.warn("testing logger!");
		return new ResponseEntity<>("Hello World", HttpStatus.OK);
	}
}