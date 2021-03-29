package uz.pdp.appapicodidingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.Course;
import uz.pdp.appapicodidingbat.entity.Section;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.SectionDTO;
import uz.pdp.appapicodidingbat.repository.CourseRepository;
import uz.pdp.appapicodidingbat.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    final SectionRepository sectionRepository;
    final CourseRepository courseRepository;

    public SectionService(SectionRepository sectionRepository, CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }

    public List<Section> getSectionService() {
        return sectionRepository.findAll();
    }

    public Section getSection(Integer sectionId) {

        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        return optionalSection.orElse(null);

    }

    public Result addSection(SectionDTO sectionDTO) {

        boolean existsByName = sectionRepository.existsByName(sectionDTO.getName());
        if (existsByName)
            return new Result("The Section name already exist!", false);

        Optional<Course> optionalCourse = courseRepository.findById(sectionDTO.getCourseId());
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);

        Section section = new Section();
        section.setDescription(sectionDTO.getDescription());
        section.setName(sectionDTO.getName());
        section.setCourse(optionalCourse.get());
        sectionRepository.save(section);
        return new Result("New section successfully saved.", true);
    }


    public Result editSection(Integer sectionId, SectionDTO sectionDTO) {

        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if (!optionalSection.isPresent())
            return new Result("Invalid section Id", false);

        boolean existsByNameAndIdNot = sectionRepository.existsByNameAndIdNot(sectionDTO.getName(), sectionId);
        if (existsByNameAndIdNot)
            return new Result("The Section name already exist!", false);

        Optional<Course> optionalCourse = courseRepository.findById(sectionDTO.getCourseId());
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);

        Section section = optionalSection.get();
        section.setDescription(sectionDTO.getDescription());
        section.setName(sectionDTO.getName());
        section.setCourse(optionalCourse.get());
        sectionRepository.save(section);
        return new Result("Section Edited", true);

    }
}
