package ca.sheridancollege.banwsukh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.domain.Post;


@Repository
public interface AppUserPostRatingRepository extends JpaRepository<AppUserPostRating, Long> {

	List<AppUserPostRating> findByPost(Post post);

}
