package ca.sheridancollege.banwsukh.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Component
public class PostBootstrap implements CommandLineRunner {

	@Autowired
	private PostService postService;

	@Override
	public void run(String... args) throws Exception {
		
		Post s1 = new Post();
		s1.setName("John Lennon");
		s1.setTitle("All You Need is Love");
		s1.setContent("There's nothing you can sing that can't be sung...");
		Post s2 = new Post();
		s2.setName("Paul McCartney");
		s2.setContent("When I find myself in times of trouble...");
		s2.setTitle("Let it Be");
		Post s3 = new Post();
		s3.setName("Leonard Cohen");
		s3.setTitle("Hallelujah");
		s3.setContent("Now I've heard there was a secret chord...");
		postService.save(s1);
		postService.save(s2);
		postService.save(s3);

	}

}
