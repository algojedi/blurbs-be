package ca.sheridancollege.banwsukh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.beans.AppUserReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;


public interface AppUserPostRatingService {
	
	public AppUserPostRating save(AppUserPostRating appUserPostRating);
	public List<AppUserPostRating> findAll();
	public Optional<AppUserPostRating> findById(Long id);
	public void deleteById(Long id);

}