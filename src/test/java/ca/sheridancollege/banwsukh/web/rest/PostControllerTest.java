package ca.sheridancollege.banwsukh.web.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.sheridancollege.banwsukh.beans.PostReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.services.AppUserServiceImpl;
import ca.sheridancollege.banwsukh.services.PostService;

class PostControllerTest {
    
		@Autowired
	    private PostService postService;

		@Autowired
	    private AppUserServiceImpl appUserService;

//	    private PostController postController;

	    @Test
	    void testAddPost() {
	    	  PostController postController = new PostController(postService, appUserService);
	          PostReq postReq = new PostReq();
	          postReq.setUserId(1L);
	          postReq.setTitle("Test Post");
	          postReq.setContent("Just some content");
	          AppUser user = new AppUser();
	          user.setId(1L);
//	          Mockito.when(appUserService.findById(1L)).thenReturn(user);
//	          Mockito.when(postService.save(Mockito.any(Post.class))).thenReturn(new Post());

	          // Act
	          System.out.println(postController.toString());
	          ResponseEntity<Post> response = postController.addPost(postReq);

	          // Assert
	          assert response.getStatusCode() == HttpStatus.OK;
	          assert response.getBody() != null;
	    }
}
