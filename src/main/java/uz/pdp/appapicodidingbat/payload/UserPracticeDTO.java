package uz.pdp.appapicodidingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appapicodidingbat.entity.Problem;
import uz.pdp.appapicodidingbat.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPracticeDTO {

    @NotNull(message = "User solution should be filled!")
    private String userSolution;

    @NotNull(message = "Score should be filled!")
    private Integer score;

    @NotNull(message = "Date should be filled")
    private Date date;

    @NotNull(message = "User Id should be filled")
    private Integer userId;

    @NotNull(message = "Problem Id should be filled!")
    private Integer problemId;
}
