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
public class CourseDTO {

    @NotNull(message = "Course's name should be filled!")
    private String name;

    @NotNull(message = "Course's description should be filled!")
    private String description;
}
