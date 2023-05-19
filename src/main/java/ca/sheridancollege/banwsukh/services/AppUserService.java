package ca.sheridancollege.banwsukh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.banwsukh.beans.AppUserReq;
import ca.sheridancollege.banwsukh.domain.AppUser;


public interface AppUserService {
	
	public List<AppUser> findAll();
	public AppUser findById(Long id);
	public AppUser findByNameAndPassword(String username, String password);
	public void deleteById(Long id);
	public AppUser save(AppUser appUser);
//	public UserPostRating save(UserPostRating userPostRating);

}