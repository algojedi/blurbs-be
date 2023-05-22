package ca.sheridancollege.banwsukh.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.domain.Post;
import ca.sheridancollege.banwsukh.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAllByOrderByCreationDateDesc();
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

//	@Override
//	public Post findByName(String name) {
//		return postRepository.findByName(name);
//	}
	
	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
		return;
	}


	@Override
	public Post save(Post post) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = LocalDateTime.now().format(formatter);
//		post.setCreationDate(formattedDateTime);
		post.setCreationDate(LocalDateTime.now());
		return postRepository.save(post);
	}
	

}
