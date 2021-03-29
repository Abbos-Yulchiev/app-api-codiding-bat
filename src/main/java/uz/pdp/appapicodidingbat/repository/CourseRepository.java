package uz.pdp.appapicodidingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodidingbat.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
