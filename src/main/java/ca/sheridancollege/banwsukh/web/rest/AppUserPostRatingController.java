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

import ca.sheridancollege.banwsukh.beans.AppUserPostRatingReq;
import ca.sheridancollege.banwsukh.beans.PostReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.exceptions.ResourceNotFoundException;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.services.AppUserPostRatingService;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("api")
@RestController
@AllArgsConstructor
public class AppUserPostRatingController {


	private final AppUserPostRatingService appUserPostRatingService;
	private final AppUserService appUserService;
	private final PostService postService;
	
	
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

	@GetMapping("/ratings")
	  public ResponseEntity<List<AppUserPostRating>> getAllRatings() {
		  List<AppUserPostRating> ratings = appUserPostRatingService.findAll();
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}

	@PostMapping("/rating")
	public ResponseEntity<AppUserPostRating> addRating(@RequestBody AppUserPostRatingReq rating) {
	    Optional<AppUser> user = appUserService.findById(rating.getUserId());
	    Optional<Post> post = postService.findById(rating.getPostId());
	    if (user.isEmpty() || post.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    AppUserPostRating appUserPostRating = new AppUserPostRating(0L,user.get(), post.get(), rating.getRating());
	    AppUserPostRating savedRating = appUserPostRatingService.save(appUserPostRating);
	    return ResponseEntity.ok(savedRating);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAppUserPostRating(@PathVariable Long id) {
	    try {
	        appUserPostRatingService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	}



}
