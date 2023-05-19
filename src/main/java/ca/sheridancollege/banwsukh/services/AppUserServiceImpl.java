package ca.sheridancollege.banwsukh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.domain.AppUser;
//import ca.banwsukh.data.entities.UserPostRating;
//import ca.banwsukh.repositories.UserPostRatingRepository;
import ca.sheridancollege.banwsukh.repositories.AppUserRepository;


@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
//	private UserPostRatingRepository userPostRatingRepository;

	public List<AppUser> findAll() {
		return appUserRepository.findAll();
	}

	public AppUser findById(Long id) {
		return appUserRepository.findById(id).get();
	}

	public AppUser findByNameAndPassword(String username, String password) {
//		return appUserRepository.findById(id).get();
		return appUserRepository.findByNameAndPassword(username, password);
	}

	public void deleteById(Long id) {
		appUserRepository.deleteById(id);
		return;
	}

	public AppUser save(AppUser appUser) {
//		AppUser appUser = new AppUser();
//		appUser.setName(appUser.getUsername());
//		appUser.setPassword(appUser.getPassword());
		return appUserRepository.save(appUser);
	}

//	public UserPostRating save(UserPostRating userPostRating) {
//		return userPostRatingRepository.save(userPostRating);
//	}


}