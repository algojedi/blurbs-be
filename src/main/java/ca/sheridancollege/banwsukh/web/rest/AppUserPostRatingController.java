package ca.sheridancollege.banwsukh.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.banwsukh.beans.PostReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.services.AppUserPostRatingService;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

//@CrossOrigin(origins="http://localhost:8080")
@CrossOrigin(origins = "*")
@RequestMapping("api")
@RestController
@AllArgsConstructor
public class AppUserPostRatingController {


//	@Autowired
//	private final PostService postService;

	@Autowired
	private final AppUserPostRatingService appUserPostRatingService;

	@GetMapping("/ratings")
	  public ResponseEntity<List<AppUserPostRating>> getAllRatings() {
		  List<AppUserPostRating> ratings = appUserPostRatingService.findAll();
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}

	/*
	@PostMapping("/rating")
	public ResponseEntity<AppUserPostRating> addRating(@RequestBody AppUserPostRatingReq post) {
		AppUserPostRating p = new AppUserPostRating();
		AppUser user = appUserService.findById(post.getUserId());
		p.setAppUser(user);
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		AppUserPostRating savedAppUserPostRating = postService.save(p);
		return new ResponseEntity<AppUserPostRating>(savedAppUserPostRating, HttpStatus.OK);
	}


	*/

	@DeleteMapping(value = "/{id}")
	public void deleteAppUserPostRating(@PathVariable Long id) {
		appUserPostRatingService.deleteById(id);
	}


	@GetMapping(value = "/rating/{id}")
	public ResponseEntity<AppUserPostRating> getRating(@PathVariable Long id) {
	    Optional<AppUserPostRating> ratingOptional = appUserPostRatingService.findById(id);
	    if (ratingOptional.isPresent()) {
	        AppUserPostRating rating = ratingOptional.get();
	        return ResponseEntity.ok(rating);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	@Override
	public String toString() {
		return super.toString() + "-------- RATING ------";
	}
}
