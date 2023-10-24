package ca.sheridancollege.banwsukh.beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostReq {
//    private String title;
    private String quillContent;
    @Size(min = 10, message = "content should have at least 4 characters")
    private String htmlContent;
    @NotNull
    private long userId;
    private List<String> tags = new ArrayList<String>();
}
