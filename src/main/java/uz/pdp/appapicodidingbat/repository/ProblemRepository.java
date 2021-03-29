package uz.pdp.appapicodidingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodidingbat.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
