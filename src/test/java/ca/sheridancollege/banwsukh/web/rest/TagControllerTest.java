package ca.sheridancollege.banwsukh.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.domain.Tag;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.repositories.TagRepository;
import ca.sheridancollege.banwsukh.services.PostServiceImpl;

@ExtendWith(MockitoExtension.class)
class TagControllerTest {

	@Mock
	private PostRepository postRepository;

	@Mock
	private TagRepository tagRepository;

	@InjectMocks
	private PostServiceImpl postService;

	// This test is more for the PostServiceImpl method: deleteTag
	@Test
	void testDeleteTag() {
		// Mock data
		Long postId = 1L;
		Long tagId = 2L;

		// Mock behavior of postRepository.findById()
		Post post = new Post();
		post.setId(postId);

		// Mock behavior of tagRepository.findById()
		Tag tag = new Tag();
		tag.setId(tagId);

		// Mock existing relationship
		Set<Tag> tags = new HashSet<>();
		tags.add(tag);
		post.setTags(tags);

		when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

		// Call the method under test
		postService.deleteTag(postId, tagId);

		// Verify the expected behavior
		verify(postRepository).findById(postId);
		verify(tagRepository).findById(tagId);

		// Verify that the relationship is correctly updated
		assertEquals(0, post.getTags().size());

		// Verify that postRepository.save() and tagRepository.save() were called
		verify(postRepository).save(post);
		verify(tagRepository).save(tag);
	}

	// ... (remaining test cases)
}
