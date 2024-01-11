package TestJPA.Controller;

import TestJPA.Entity.Course;
import TestJPA.Services.CourseServices;
import com.google.gson.Gson;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/version1.0")
public class CourseController {

    @Autowired
    private CourseServices courseServices;

    @PostMapping(value = "course/add")
    public Course AddNew(@RequestBody String newCourse) {
        Gson gson = new Gson();
        Course course = gson.fromJson(newCourse, Course.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Course>> violation = validator.validate(course);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return courseServices.AddNew(course);
        }
        return null;
    }

    @PutMapping(value = "course/remake")
    public Course Remake(@RequestBody String remakeCourse) {
        Gson gson = new Gson();
        Course course = gson.fromJson(remakeCourse, Course.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Course>> violation = validator.validate(course);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return courseServices.Remake(course);
        }
        return null;
    }

    @DeleteMapping(value = "course/delete")
    public Course Delete(@RequestParam int courseId) {
        return courseServices.Delete(courseId);
    }

    @GetMapping(value = "course/get")
    public List<Course> GetAllCourse() {
        return courseServices.GetAllCourse();
    }

    @GetMapping(value = "course/getByName")
    public List<Course> GetByName(@RequestParam String courseName) {
        return courseServices.GetByName(courseName);
    }

    @GetMapping(value = "course/getByPage")
    public Page<Course> GetByPage( Integer page, Integer size) {
        if (page == null) page =1;
        if (size == null) size =1;
        Pageable pageable = PageRequest.of(page,size);
        return courseServices.GetByPage(pageable);
    }
}
