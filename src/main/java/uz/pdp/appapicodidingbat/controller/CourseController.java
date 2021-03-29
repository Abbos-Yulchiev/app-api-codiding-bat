package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.Course;
import uz.pdp.appapicodidingbat.payload.CourseDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.service.CourseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Getting All courses
     *
     * @return courseList
     */
    @GetMapping
    public ResponseEntity<?> getCourses() {

        List<Course> course = courseService.getCourses();
        return ResponseEntity.ok(course);
    }

    /**
     * Getting a Course by id
     *
     * @param courseId
     * @return Course
     */
    @GetMapping(value = "/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable Integer courseId) {

        Course course = courseService.getCourse(courseId);
        return ResponseEntity.ok(course);
    }

    /**
     * Posting new Course
     *
     * @return Result
     **/
    @PostMapping
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDTO courseDTO) {

        Result result = courseService.addCourse(courseDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }


    /**
     * Posting new Course
     *
     * @param courseId
     * @return Result
     **/
    @PutMapping(value = "/{courseId}")
    public ResponseEntity<?> editCourse(@PathVariable Integer courseId, @Valid @RequestBody CourseDTO courseDTO) {

        Result result = courseService.editCourse(courseId, courseDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    /**Deleting Course
     * @param courseId
     * @return Result*/
    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {

        Result result = courseService.deleteCourse(courseId);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }
}
