package ca.sheridancollege.banwsukh.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "app_user")
@Entity
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@JsonIgnore
	private String password;

	public AppUser(String name) {
		this.name = name;
	}

	public static AppUser create(String name, String password) {
		AppUser appUser = new AppUser();
		appUser.setName(name);
		appUser.setPassword(password);
		return appUser;
	}
}

// This method may make a little more sense for 'likedPosts'
/*
 * @JsonIgnore
 * 
 * @ManyToMany
 * 
 * @JoinTable(name = "app_user_posts", joinColumns = @JoinColumn(name =
 * "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id")) private
 * Set<Post> posts = new HashSet<>();
 */