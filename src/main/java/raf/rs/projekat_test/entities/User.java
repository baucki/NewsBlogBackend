package raf.rs.projekat_test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.rs.projekat_test.entities.enums.UserStatus;
import raf.rs.projekat_test.entities.enums.UserType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private UserType type;
    private String password;
    private UserStatus status;

}
