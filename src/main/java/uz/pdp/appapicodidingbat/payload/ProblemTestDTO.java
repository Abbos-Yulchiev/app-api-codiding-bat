package uz.pdp.appapicodidingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemTestDTO {

    @NotNull(message = "Arguments should be filled!")
    private String arguments;

    @NotNull(message = "Result should be filled!")
    private String result;

    @NotNull(message = "Problem Id should be filled!")
    private Integer problemId;
}
