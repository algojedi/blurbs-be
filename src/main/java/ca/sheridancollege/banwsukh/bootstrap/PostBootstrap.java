package ca.sheridancollege.banwsukh.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.beans.AppUserReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.services.AppUserPostRatingService;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class PostBootstrap implements CommandLineRunner {

	@Autowired
	private PostService postService;

	@Autowired
	private AppUserPostRatingService appUserPostRatingService;

	@Autowired
	private AppUserServiceImpl appUserService;

	@Override
	public void run(String... args) throws Exception {

		// Create instances of AppUser and set their properties
		AppUser johnLennon = new AppUser();
		johnLennon.setName("John Lennon");
		johnLennon.setPassword("test");
		AppUser paulMcCartney = new AppUser();
		paulMcCartney.setName("Paul McCartney");
		paulMcCartney.setPassword("test");
		AppUser leonardCohen = new AppUser();
		leonardCohen.setName("Leonard Cohen");
		leonardCohen.setPassword("test");

		// Create instances of Post and set their properties
		Post p1 = new Post();
		p1.setTitle("All You Need is Love");
		p1.setContent("There's nothing you can sing that can't be sung...");

		Post p2 = new Post();
		p2.setTitle("Let it Be");
		p2.setContent("When I find myself in times of trouble...");

		Post p3 = new Post();
		p3.setTitle("Hallelujah");
		p3.setContent("Now I've heard there was a secret chord...");

		// Retrieve the respective AppUser objects for each post based on their names
//		AppUser userJohn = appUserService.findByNameAndPassword("John Lennon", "test");
//		AppUser userPaul = appUserService.findByNameAndPassword("Paul McCartney", "test");
//		AppUser userLeonard = appUserService.findByNameAndPassword("Leonard Cohen", "test");

		// Assign the retrieved AppUser objects to the AppUser property of each Post
		johnLennon = appUserService.save(johnLennon);
		paulMcCartney = appUserService.save(paulMcCartney);
		leonardCohen = appUserService.save(leonardCohen);
		p1.setAppUser(johnLennon);
		p2.setAppUser(paulMcCartney);
		p3.setAppUser(leonardCohen);

		// Save the Post objects using the postService.save() method
		p1 = postService.save(p1);
		p2 = postService.save(p2);
		p3 = postService.save(p3);

		// Save ratings
		AppUserPostRating userPostRating = new AppUserPostRating();
		userPostRating.setPost(p1);
		userPostRating.setUser(leonardCohen);
		userPostRating.setRating(4.5);
		appUserPostRatingService.save(userPostRating);
	}

}
