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
	private AppUserService appUserService;

	@Override
	public void run(String... args) throws Exception {

		// Create instances of AppUser and set their properties
		AppUser johnLennon = AppUser.create("John Lennon", "test");
		AppUser paulMcCartney = AppUser.create("Paul McCartney", "test");
		AppUser leonardCohen = AppUser.create("Leonard Cohen", "test");

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

		// Assign the retrieved AppUser objects to the AppUser property of each Post
		appUserService.save(johnLennon);
		appUserService.save(paulMcCartney);
		appUserService.save(leonardCohen);
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
		userPostRating = appUserPostRatingService.save(userPostRating);
		postService.updateAverageRating(p1);
	}
}
