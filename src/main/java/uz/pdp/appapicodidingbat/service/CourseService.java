package uz.pdp.appapicodidingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.Course;
import uz.pdp.appapicodidingbat.payload.CourseDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**Getting all Courses*/
    public List<Course> getCourses() {
        List<Course> all = courseRepository.findAll();
        return all;
    }

    public Course getCourse(Integer courseId) {

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElse(null);
    }

    public Result addCourse(CourseDTO courseDTO) {

        boolean existsByName = courseRepository.existsByName(courseDTO.getName());
        if (existsByName)
            return new Result("The course name already exist", false);

        Course course = new Course();
        course.setDescription(courseDTO.getDescription());
        course.setName(course.getName());
        courseRepository.save(course);
        return new Result("New Course successfully saved.", true);

    }

    public Result editCourse(Integer courseId, CourseDTO courseDTO) {

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);

        boolean existsByNameAndIdNot = courseRepository.existsByNameAndIdNot(courseDTO.getName(), courseId);
        if (existsByNameAndIdNot)
            return new Result("Course name already exist, enter another one!", false);

        Course course = optionalCourse.get();
        course.setName(courseDTO.getName());
        course.setDescription(course.getDescription());
        courseRepository.save(course);
        return new Result("Course edited.", true);
    }

    public Result deleteCourse(Integer courseId) {

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);
        courseRepository.deleteById(courseId);
        return new Result("Course deleted!", true);
    }
}
