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
	    AppUser bobDylan = AppUser.create("Bob Dylan", "test");
	    AppUser davidBowie = AppUser.create("David Bowie", "test");

	    // Save the AppUser objects using the appUserService.save() method
	    appUserService.save(johnLennon);
	    appUserService.save(paulMcCartney);
	    appUserService.save(leonardCohen);
	    appUserService.save(bobDylan);
	    appUserService.save(davidBowie);

	    // Create instances of Post and set their properties
	    Post p1 = new Post();
	    p1.setQuillContent("There's nothing you can sing that can't be sung...");
	    p1.setHtmlContent("<p>There's nothing you can sing that can't be sung...</p>");
	    p1.setAppUser(johnLennon);

	    Post p2 = new Post();
	    p2.setQuillContent("When I find myself in times of trouble...");
	    p2.setHtmlContent("<p>When I find myself in times of trouble...</p>");
	    p2.setAppUser(paulMcCartney);

	    Post p3 = new Post();
	    p3.setQuillContent("Now I've heard there was a secret chord...");
	    p3.setHtmlContent("<h4>Now I've heard there was a secret chord...</h4>");
	    p3.setAppUser(leonardCohen);

	    Post p4 = new Post();
	    p4.setQuillContent("How many roads must a man walk down...");
	    p4.setHtmlContent("<h3>How many roads must a man walk down...</h3>");
	    p4.setAppUser(bobDylan);

	    Post p5 = new Post();
	    p5.setQuillContent("Ground Control to Major Tom...");
	    p5.setHtmlContent("<p>Ground Control to Major Tom...</p>");
	    p5.setAppUser(davidBowie);

	    // Save the Post objects using the postService.save() method
	    p1 = postService.save(p1);
	    p2 = postService.save(p2);
	    p3 = postService.save(p3);
	    p4 = postService.save(p4);
	    p5 = postService.save(p5);

	    // Save ratings
	    AppUserPostRating userPostRating1 = new AppUserPostRating();
	    userPostRating1.setPost(p1);
	    userPostRating1.setUser(leonardCohen);
	    userPostRating1.setRating(4.5);
	    userPostRating1 = appUserPostRatingService.save(userPostRating1);
	    postService.updateAverageRating(p1);

	    AppUserPostRating userPostRating2 = new AppUserPostRating();
	    userPostRating2.setPost(p2);
	    userPostRating2.setUser(johnLennon);
	    userPostRating2.setRating(4.0);
	    userPostRating2 = appUserPostRatingService.save(userPostRating2);
	    postService.updateAverageRating(p2);

	    AppUserPostRating userPostRating3 = new AppUserPostRating();
	    userPostRating3.setPost(p3);
	    userPostRating3.setUser(paulMcCartney);
	    userPostRating3.setRating(5.0);
	    userPostRating3 = appUserPostRatingService.save(userPostRating3);
	    postService.updateAverageRating(p3);

	    AppUserPostRating userPostRating4 = new AppUserPostRating();
	    userPostRating4.setPost(p4);
	    userPostRating4.setUser(davidBowie);
	    userPostRating4.setRating(4.2);
	    userPostRating4 = appUserPostRatingService.save(userPostRating4);
	    postService.updateAverageRating(p4);

	    AppUserPostRating userPostRating5 = new AppUserPostRating();
	    userPostRating5.setPost(p5);
	    userPostRating5.setUser(bobDylan);
	    userPostRating5.setRating(4.8);
	    userPostRating5 = appUserPostRatingService.save(userPostRating5);
	    postService.updateAverageRating(p5);
	}

}
