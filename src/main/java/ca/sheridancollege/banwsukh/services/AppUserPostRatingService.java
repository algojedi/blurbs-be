package ca.sheridancollege.banwsukh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.beans.AppUserPostRatingReq;
import ca.sheridancollege.banwsukh.beans.AppUserReq;
import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.domain.Post;


public interface AppUserPostRatingService {
	
	public AppUserPostRating save(AppUserPostRating rating);
	public List<AppUserPostRating> findAll();
	public Optional<AppUserPostRating> findById(Long id);
	public void deleteById(Long id);
	public List<Double> getRatingsByPost(Post post);

}