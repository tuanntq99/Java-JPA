package TestJPA.Controller;

import TestJPA.Entity.Trainee;
import TestJPA.Services.TraineeServices;
import com.google.gson.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/version1.0")
public class TraineeController {
    @Autowired
    private TraineeServices traineeServices;

    @PostMapping(value = "trainee/add")
    public Trainee AddNew(@RequestBody String newTrainee) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Trainee trainee = gson.fromJson(newTrainee, Trainee.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Trainee>> violation = validator.validate(trainee);
        violation.forEach(x -> { System.out.println(x.getMessage()); });

        if (violation.size() == 0) {
            return traineeServices.AddNew(trainee);
        }
        return null;
    }

    @PutMapping(value = "trainee/remake")
    public Trainee Remake(@RequestBody String remakeTrainee) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Trainee newTrainee = gson.fromJson(remakeTrainee, Trainee.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Trainee>> violation = validator.validate(newTrainee);
        violation.forEach(x -> { System.out.println(x.getMessage()); });

        if (violation.size() == 0) {
            return traineeServices.Remake(newTrainee);
        }
        System.out.println("fail access Services");
        return null;
    }

    @DeleteMapping(value = "trainee/delete")
    public Trainee Delete(@RequestParam int traineeId) {
        return traineeServices.Delete(traineeId);
    }

    @GetMapping(value = "trainee/getBy")
    public List<Trainee> GetBy(@RequestParam String fullName, String email) {
        return traineeServices.GetBy(fullName, email);
    }
}
