package uz.pdp.appapicodidingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    @NotNull(message = "Section name should be filled!")
    private String name;

    @NotNull(message = "Section description should be filled")
    private String description;

    @NotNull(message = "Course Id should be filled!")
    private Integer courseId;
}
