package ca.sheridancollege.banwsukh.services;

import java.util.Optional;
import java.util.Set;

import ca.sheridancollege.banwsukh.domain.Tag;

public interface TagService {

	public Optional<Tag> findByName(String name);
	
	public Set<Tag> findExistingTagNames(Set<String> tagNames);

	public Optional<Tag> findById(Long id);

	public Tag save(Tag tag);

	public Set<Tag> saveAll(Set<Tag> tags);

	public boolean deleteById(Long id);

}
