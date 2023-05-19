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
public class PostController {


	@Autowired
	private final PostService postService;

	@Autowired
	private final AppUserService appUserService;


//	@GetMapping(value = {"/",""})
//	public List<Post> getPosts() {
//		return postService.findAll();
//	}

	@GetMapping("/posts")
	  public ResponseEntity<List<Post>> getAllPosts() {
		  List<Post> posts = postService.findAll();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@PostMapping("/post")
	public ResponseEntity<Post> addPost(@RequestBody PostReq post) {
//		return blogPostService.save(blogPost); 
		Post p = new Post();
//		System.out.println("-----");
//		System.out.println(post.getUserId());
//		System.out.println(post.getContent());
		AppUser user = appUserService.findById(post.getUserId());
		p.setAppUser(user);
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		Post savedPost = postService.save(p);
		return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePost(@PathVariable Long id) {
		System.out.println("reached post controller delete mapping with id: " + id);
		postService.deleteById(id);
		
	}

	@Override
	public String toString() {
		return super.toString() + "-------- : ) ------";
	}
	/*
	@GetMapping(value = "/{id}")
	public Post getAppointment(@PathVariable Long id) {
		return studentRepository.findById(id).get();
	}

	@PostMapping(consumes = "application/json")
	public Post postAppointment(@RequestBody Post a) {
		return studentRepository.save(a);
	}

	@DeleteMapping(value = "/{id}")
	public Post deleteAppointment(@PathVariable Long id) {
		Post student = studentRepository.findById(id).get();
		studentRepository.deleteById(id);
		return student;
	}

	@PutMapping(consumes = "application/json")
	public String putAppointmentCollection(@RequestBody List<Post> appointmentList) {
		studentRepository.deleteAll();
		studentRepository.saveAll(appointmentList);
		return "Total Records: " + studentRepository.count();
	}

	@PutMapping(value = "/{id}")
	public void updateAppointment(@RequestBody Post student) {
		// TODO: Preconditions.checkNotNull(resource);
		// RestPreconditions.checkNotNull(service.getById(resource.getId()));
		studentRepository.save(student);
	}
	*/
}
