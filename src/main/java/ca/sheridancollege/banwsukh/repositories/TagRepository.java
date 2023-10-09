package ca.sheridancollege.banwsukh.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.banwsukh.domain.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
	Set<Tag> saveAll(Set<Tag> tags);
    Optional<Tag> findByName(String name);
	Set<Tag> findNamesByNameIn(Set<String> tagNames);
	void deleteById(Long id); 
	
}
