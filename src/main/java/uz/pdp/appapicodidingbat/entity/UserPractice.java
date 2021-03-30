package uz.pdp.appapicodidingbat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userSolution;

    private Integer score;

    private Date date;

    @ManyToOne
    private User user;

    @OneToOne
    private Problem problem;
}
