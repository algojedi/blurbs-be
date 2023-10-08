package ca.sheridancollege.banwsukh.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
//	private String title;
	@Column(columnDefinition = "LONGTEXT")
	private String quillContent;
	@Column(columnDefinition = "LONGTEXT")
	private String htmlContent;
	private LocalDateTime creationDate = LocalDateTime.now();
	private Double averageRating;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "app_user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AppUser appUser;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "posts")
	private Set<Tag> tags = new HashSet<>();

	public void addTag(Tag t) {
		this.tags.add(t);
		t.getPosts().add(this);
	}

	public void addTags(Set<Tag> newTags) {
		newTags.forEach(t -> {
			t.getPosts().add(this);
		});
		tags.addAll(newTags);
	}

	public void removeTag(Tag t) {
		this.tags.remove(t);
		t.getPosts().remove(this);
	}

	@Override
	public String toString() {
		return "Post{" + "id=" + id + ", quillContent='" + quillContent + '\'' + ", htmlContent='" + htmlContent + '\''
				+ ", creationDate=" + creationDate + ", averageRating=" + averageRating + ", appUser=" +
				// + appUser.getName() + ", tags=" + " ? " + // tags.toString() +
				'}';
	}
}
