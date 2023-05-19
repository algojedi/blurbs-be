package ca.sheridancollege.banwsukh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;


@Repository
public interface AppUserPostRatingRepository extends JpaRepository<AppUserPostRating, Long> {

}
