package uz.pdp.appapicodidingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodidingbat.entity.Course;
import uz.pdp.appapicodidingbat.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
