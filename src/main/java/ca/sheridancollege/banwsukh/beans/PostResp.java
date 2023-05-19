package ca.sheridancollege.banwsukh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResp {
	private long id;
    private String title;
    private String content;
    private int rating;
    private String createdOn;
}
