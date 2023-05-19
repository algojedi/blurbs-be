package ca.sheridancollege.banwsukh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUserReq {
    private String username;
    private String password;
//    private String email;
}
