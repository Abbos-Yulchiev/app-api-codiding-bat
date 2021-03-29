package uz.pdp.appapicodidingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {

    @NotNull(message = "Problem title should be filled!")
    private String title;

    @NotNull(message = "Problem body should be filled!")
    private String body;

    @NotNull(message = "Problem should be filled!")
    private String problem;

    @NotNull(message = "Solution should be filled!")
    private String solution;

    @NotNull(message = "Problem's Solution Id should be filled!")
    private Integer sectionId;

}
