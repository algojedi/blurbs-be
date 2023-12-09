package ca.sheridancollege.banwsukh.services;

import java.util.List;
import java.util.Optional;

import ca.sheridancollege.banwsukh.domain.Post;

public interface PostService {

	public List<Post> findAll();
	public Optional<Post> findById(Long id);
	public Post save(Post post);
	public void deleteById(Long id);
	public void deleteTag(Long postId, Long tagId);
//	public void calculateAndUpdateAverageRating(Post post);
	public void updateAverageRating(Post post);
}
