package TestJPA.Controller;

import TestJPA.Entity.CourseType;
import TestJPA.Services.CourseTypeServices;
import com.google.gson.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping(value = "api/version1.0")
public class CourseTypeController {
    @Autowired
    private CourseTypeServices courseTypeServices;

    @PostMapping(value = "courseType/add")
    public CourseType AddNew(@RequestBody String courseType) {
        Gson gson = new Gson();
        CourseType newct = gson.fromJson(courseType, CourseType.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<CourseType>> violation = validator.validate(newct);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return courseTypeServices.AddNew(newct);
        }
        return null;
    }

    @PutMapping(value = "courseType/remake")
    public CourseType Remake(@RequestBody String courseType) {
        Gson gson = new Gson();
        CourseType newct = gson.fromJson(courseType, CourseType.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<CourseType>> violation = validator.validate(newct);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return courseTypeServices.Remake(newct);
        }
        return null;
    }

    @DeleteMapping(value = "courseType/delete")
    public CourseType Delete(@RequestParam int courseTypeId) {
        return courseTypeServices.Delete(courseTypeId);
    }
}
