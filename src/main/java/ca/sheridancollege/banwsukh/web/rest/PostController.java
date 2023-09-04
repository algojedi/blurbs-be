package ca.sheridancollege.banwsukh.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("api")
@RestController
@AllArgsConstructor
public class PostController {

//	@Autowired
	private final PostService postService;

//	@Autowired
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
		Optional<AppUser> userOptional = appUserService.findById(post.getUserId());
		if (userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		AppUser user = userOptional.get();
		p.setAppUser(user);
//		p.setTitle(post.getTitle());
		p.setQuillContent(post.getQuillContent());
		p.setHtmlContent(post.getHtmlContent());
		Post savedPost = postService.save(p);
		return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
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
