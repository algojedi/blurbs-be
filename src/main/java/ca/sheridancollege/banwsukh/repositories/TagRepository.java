package ca.sheridancollege.banwsukh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.banwsukh.domain.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    Optional<List<Tag>> findByName(String name);
}