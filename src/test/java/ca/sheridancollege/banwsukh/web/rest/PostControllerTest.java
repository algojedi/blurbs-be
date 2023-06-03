package ca.sheridancollege.banwsukh.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.sheridancollege.banwsukh.beans.PostReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.PostService;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private PostController postController;

//    @Before

	@Test
	public void testAddPost() {
		// Mock data
		PostReq postReq = new PostReq();
		postReq.setUserId(1L);
		postReq.setTitle("Test Title");
		postReq.setQuillContent("Test Content");

		// Mock behavior of appUserService.findById()
		AppUser user = new AppUser();
		user.setId(1L);
		Optional<AppUser> userOptional = Optional.of(user);

		when(appUserService.findById(1L)).thenReturn(userOptional);

		// Mock behavior of postService.save()
		Post savedPost = new Post();
		savedPost.setId(1L);
		when(postService.save(any(Post.class))).thenReturn(savedPost);

		// Call the method under test
		ResponseEntity<Post> response = postController.addPost(postReq);

		// Verify the expected behavior
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(savedPost, response.getBody());

		// Verify that appUserService.findById() was called with the correct argument
		verify(appUserService).findById(postReq.getUserId());

		// Verify that postService.save() was called with the correct argument
		verify(postService).save(any(Post.class));
	}
}
