package ca.sheridancollege.banwsukh.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//@CrossOrigin(origins="http://localhost:8080")
@CrossOrigin(origins = "*")
@RequestMapping("api")
@RestController
@AllArgsConstructor
public class AppUserController {

	@Autowired
	private final AppUserService appUserService;

	@GetMapping("/users")
	  public ResponseEntity<List<AppUser>> getAllUsers() {
		  List<AppUser> users = appUserService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/*
	@PostMapping("/user")
	public ResponseEntity<Post> addPost(@RequestBody PostReq post) {
		Post p = new Post();
		AppUser user = appUserService.findById(post.getUserId());
		p.setAppUser(user);
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		Post savedPost = postService.save(p);
		return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUser(@PathVariable Long id) {
		System.out.println("reached post controller delete mapping with id: " + id);
		postService.deleteById(id);
		
	}
	*/

	@Override
	public String toString() {
		return super.toString() + "-------- USER ------";
	}

}
