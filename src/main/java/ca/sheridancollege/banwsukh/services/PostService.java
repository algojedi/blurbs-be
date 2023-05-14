package ca.sheridancollege.banwsukh.services;

import java.util.List;

import ca.sheridancollege.banwsukh.domain.Post;

public interface PostService {

	public List<Post> findAll();
	public Post findById(Long id);
	public Post findByName(String name);
	public Post save(Post post);
	public void deleteById(Long id);
}
