package ca.sheridancollege.banwsukh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.domain.AppUser;
import ca.sheridancollege.banwsukh.domain.AppUserPostRating;
import ca.sheridancollege.banwsukh.repositories.AppUserPostRatingRepository;
//import ca.banwsukh.data.entities.UserPostRating;
//import ca.banwsukh.repositories.UserPostRatingRepository;
import ca.sheridancollege.banwsukh.repositories.AppUserRepository;


@Service
public class AppUserPostRatingServiceImpl implements AppUserPostRatingService {
	
	@Autowired
	private AppUserPostRatingRepository appUserPostRatingRepository;

	public List<AppUserPostRating> findAll() {
		return appUserPostRatingRepository.findAll();
	}

	public AppUserPostRating findById(Long id) {
		return appUserPostRatingRepository.findById(id).get();
	}

	public AppUserPostRating save(AppUserPostRating appUserPostRating) {
		return appUserPostRatingRepository.save(appUserPostRating);
	}

}