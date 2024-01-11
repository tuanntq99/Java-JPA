package TestJPA.Controller;

import TestJPA.Entity.Register;
import TestJPA.Services.RegisterServices;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/version1.0")
public class RegisterController {
    @Autowired
    private RegisterServices registerServices;

    @PostMapping(value = "register/add")
    public Register AddNew(@RequestBody String newRegister) {
        Gson gson = new Gson();
        Register register = gson.fromJson(newRegister, Register.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Register>> violation = validator.validate(register);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return registerServices.AddNew(register);
        }
        return null;
    }

    @PutMapping(value = "register/remake")
    public Register Remake(@RequestBody String remakeRegister) {
        Gson gson = new Gson();
        Register register = gson.fromJson(remakeRegister, Register.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Register>> violation = validator.validate(register);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return registerServices.Remake(register);
        }
        return null;
    }

    @DeleteMapping(value = "register/delete")
    public Register Delete(@RequestParam int registerId) {
        return registerServices.Delete(registerId);
    }

    @GetMapping(value = "register/get")
    public List<Register> GetAll() {
        return registerServices.GetAll();
    }

    @GetMapping(value = "register/getByPage")
    public Page<Register> GetByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return registerServices.GetByPage(pageable);
    }
}
