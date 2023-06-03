package ca.sheridancollege.banwsukh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostReq {
//    private String title;
    private String quillContent;
    private String htmlContent;
    private long userId;
}
