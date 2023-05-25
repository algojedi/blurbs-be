package ca.sheridancollege.banwsukh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.banwsukh.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByOrderByCreationDateDesc();

	@Query("SELECT AVG(rating) FROM AppUserPostRating WHERE post = :post")
    Double calculateAverageRating(@Param("post") Post post);
}
