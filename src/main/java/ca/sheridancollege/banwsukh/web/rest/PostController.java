package ca.sheridancollege.banwsukh.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.UpdatableSqlQuery;
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
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.domain.Tag;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.repositories.TagRepository;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;
import ca.sheridancollege.banwsukh.services.TagService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("api")
@RestController
@AllArgsConstructor
public class PostController {
	private final PostService postService;

	private final TagService tagService;

	private final AppUserService appUserService;

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postService.findAll();
		logger.warn("testing logger!");
		System.out.println("writin gout the posts...");
		System.out.println(posts.get(0));
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@GetMapping(value = "/posts/{id}")
	public ResponseEntity<Post> getPost(@PathVariable Long id) {
		return postService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/post")
	public ResponseEntity<Post> addPost(@RequestBody PostReq post) {
		Post p = new Post();

		// TODO: validate post using Validators

		System.out.println("post tags in controller are ...");
		System.out.println(post.getTags());
		List<String> tagsList = post.getTags();
		Set<String> tagNames = new HashSet<>(tagsList); 
		Set<Tag> existingTags = tagService.findExistingTagNames(tagNames);
		Set<Tag> newTagsList = tagNames.stream()
		        .map(tagName -> existingTags.stream()
		                .filter(tag -> tag.getName().equals(tagName))
		                .findFirst()
		                .orElse(new Tag(tagName)))
		        .collect(Collectors.toSet());

		Optional<AppUser> userOptional = appUserService.findById(post.getUserId());
		if (userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		AppUser user = userOptional.get();

		p.setAppUser(user);
		p.setQuillContent(post.getQuillContent());
		p.setHtmlContent(post.getHtmlContent());

		try {
			p.addTags(newTagsList);
			Set<Tag> updatedTags = tagService.saveAll(newTagsList);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(value = "/posts/{id}")
	public void deletePost(@PathVariable Long id) {
		System.out.println("reached post controller delete mapping with id: " + id);
		postService.deleteById(id);

	}

	@Override
	public String toString() {
		return super.toString() + "-------- : ) ------";
	}

}
