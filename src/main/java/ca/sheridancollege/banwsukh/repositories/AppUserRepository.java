package ca.sheridancollege.banwsukh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.banwsukh.domain.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByNameAndPassword(String name, String password);

}
