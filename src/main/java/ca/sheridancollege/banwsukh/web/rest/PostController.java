package ca.sheridancollege.banwsukh.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

//@CrossOrigin(origins="http://localhost:8080")
@CrossOrigin(origins = "*")
@RequestMapping("api/post")
@RestController
@AllArgsConstructor
public class PostController {


	@Autowired
	private PostService postService;


	@GetMapping(value = {"/",""})
	public List<Post> getPosts() {
		return postService.findAll();
	}

	@PostMapping(value = {"", "/"})
	public Post save(@RequestBody Post post) {
		return postService.save(post); 
	}

	@DeleteMapping(value = "/{id}")
	public void deletePost(@PathVariable Long id) {
		System.out.println("reached post controller delete mapping with id: " + id);
		postService.deleteById(id);
		
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
