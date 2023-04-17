package ucl.ee.sec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Date birthday;
    private int gender;
    private String phone;

}
