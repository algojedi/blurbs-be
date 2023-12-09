package ca.sheridancollege.banwsukh.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.domain.Tag;
import ca.sheridancollege.banwsukh.repositories.AppUserPostRatingRepository;
import ca.sheridancollege.banwsukh.repositories.PostRepository;
import ca.sheridancollege.banwsukh.repositories.TagRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private AppUserPostRatingService appUserPostRatingService;

	@Override
	public List<Post> findAll() {
		return postRepository.findAllByOrderByCreationDateDesc();
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
		return;
	}

	@Override
	@Transactional
	public void deleteTag(Long postId, Long tagId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			Optional<Tag> tagOptional = tagRepository.findById(tagId);
			if (tagOptional.isPresent()) {
				Tag tag = tagOptional.get();
				post.getTags().remove(tag);
				postRepository.save(post);
				tag.getPosts().remove(post);
				tagRepository.save(tag);
				System.out.println("saved post and tag after delete");
			} else {
				throw new EntityNotFoundException("Tag not found with id: " + tagId);
			}
		} else {
			throw new EntityNotFoundException("Post not found with id: " + postId);
		}
	}

	@Override
	public Post save(Post post) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = LocalDateTime.now().format(formatter);
//		post.setCreationDate(formattedDateTime);
//		post.setCreationDate(LocalDateTime.now());
		return postRepository.save(post);
	}

	public void updateAverageRating(Post post) {
		Double averageRating = postRepository.calculateAverageRating(post);
		post.setAverageRating(averageRating);
		postRepository.save(post);
	}
	/*
	 * public void calculateAndUpdateAverageRating(Post post) { // Retrieve the
	 * ratings associated with the post List<Double> ratings =
	 * appUserPostRatingService.getRatingsByPost(post); if (!ratings.isEmpty()) { //
	 * Calculate the average rating double sum = 0.0; for (Double rating : ratings)
	 * { sum += rating; } double averageRating = sum / ratings.size();
	 * post.setAverageRating(averageRating); } else { // No ratings available, set
	 * average rating to null or a default value post.setAverageRating(null); }
	 * postRepository.save(post); }
	 */
}
