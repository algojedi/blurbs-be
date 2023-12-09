package ca.sheridancollege.banwsukh.web.rest;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.PostService;
import ca.sheridancollege.banwsukh.services.TagService;
import lombok.AllArgsConstructor;


@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
	
	private final PostService postService;

	private final TagService tagService;

	private final AppUserService appUserService;

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    // TODO: Other methods for managing tags

	@DeleteMapping("/{tagId}/posts/{postId}")
	public ResponseEntity<?> deleteTagFromPost(@PathVariable @Min(1) Long tagId, @PathVariable @Min(1) Long postId) {
	    try {
	        logger.info("Deleting tag with id: " + tagId);
	        logger.info("Post id: " + postId);
	        postService.deleteTag(postId, tagId);
	        return ResponseEntity.ok().build();
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions and return a generic error response
	        logger.error("An error occurred while deleting the tag", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the tag");
	    }
	}

}
