package ca.sheridancollege.banwsukh.beans;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> tags = new ArrayList<String>();
}
