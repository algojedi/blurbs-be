package ca.sheridancollege.banwsukh.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import ca.sheridancollege.banwsukh.domain.Tag;
import ca.sheridancollege.banwsukh.services.AppUserService;
import ca.sheridancollege.banwsukh.services.PostService;
import ca.sheridancollege.banwsukh.services.TagService;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private AppUserService appUserService;

    @Mock
    private TagService tagService;

    @InjectMocks
    private PostController postController;

	@Test
	public void testAddPost() {
		// Mock data
		PostReq postReq = new PostReq();
		postReq.setUserId(1L);
		postReq.setQuillContent("Test Content");
		postReq.setHtmlContent("Valid HTML Content");

		// Mock behavior of appUserService.findById()
		AppUser user = new AppUser();
		user.setId(1L);
		Optional<AppUser> userOptional = Optional.of(user);
		when(appUserService.findById(1L)).thenReturn(userOptional);
		
		// Mock behavior of tagService.findExistingTagNames() and tagService.saveAll()
        Set<Tag> existingTags = new HashSet<>();
        Set<Tag> newTagsList = new HashSet<>();
        when(tagService.findExistingTagNames(any())).thenReturn(existingTags);
        when(tagService.saveAll(any())).thenReturn(newTagsList);

		ResponseEntity<Post> response = postController.addPost(postReq);

		// Verify the expected behavior
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals(user, response.getBody().getAppUser());
        assertEquals("Test Content", response.getBody().getQuillContent());
        assertEquals("Valid HTML Content", response.getBody().getHtmlContent());

		// Verify that appUserService.findById() was called with the correct argument
		verify(appUserService).findById(postReq.getUserId());
		verify(tagService).findExistingTagNames(any());
        verify(tagService).saveAll(any());
	}
}
