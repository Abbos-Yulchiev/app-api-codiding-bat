package uz.pdp.appapicodidingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Username should be filled!")
    private String userName;

    @NotNull(message = "User's first name should be filled!")
    private String firstName;

    @NotNull(message = "User's last name should be filled!")
    private String lastName;

    @NotNull(message = "Email should be filled!")
    private String email;

    @NotNull(message = "Password should be filled!")
    private String password;


}
