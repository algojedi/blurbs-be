package ca.sheridancollege.banwsukh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.banwsukh.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Post findByName(String name);
	List<Post> findAllByOrderByCreationDateDesc();
	

}
