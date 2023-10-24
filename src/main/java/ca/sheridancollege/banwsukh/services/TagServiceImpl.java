package ca.sheridancollege.banwsukh.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.sheridancollege.banwsukh.domain.Tag;
import ca.sheridancollege.banwsukh.repositories.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Optional<Tag> findByName(String name) {
	    return tagRepository.findByName(name);
	}

	@Override
	public Optional<Tag> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag save(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteById(Long id) {
        try {
            tagRepository.deleteById(id);
            return true; // Successful deletion
        } catch (Exception ex) {
//            throw new RuntimeException("Error deleting tag with ID " + id, ex);
        	// TODO: handle errors 
        	return false;
        }
    }

	@Override
	public Set<Tag> saveAll(Set<Tag> tags) {
		List<Tag> savedTags = tagRepository.saveAll(tags);
		return new HashSet<>(savedTags);
	}
	
	 public Set<Tag> findExistingTagNames(Set<String> tagNames) {
	        return new HashSet<>(tagRepository.findNamesByNameIn(new ArrayList<>(tagNames)));
//	        return tagRepository.findNamesByNameIn(tagNames);
//	        return new HashSet<>(existingTagNames);
	    }

}
