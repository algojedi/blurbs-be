package ca.sheridancollege.banwsukh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingResp {
	private long userId;
	private long postId;
    private Double rating;
    private String createdOn;
}
