package TestJPA.Controller;

import TestJPA.Entity.Authority;
import TestJPA.Entity.Course;
import TestJPA.Services.AuthorityServices;
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
public class AuthorityController {
    @Autowired
    private AuthorityServices authorityServices;

    @PostMapping(value = "authority/add")
    public Authority AddNew(@RequestBody String newAuthority) {
        Gson gson = new Gson();
        Authority authority = gson.fromJson(newAuthority, Authority.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Authority>> violation = validator.validate(authority);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return authorityServices.AddNew(authority);
        }
        return null;
    }

    @PutMapping(value = "authority/remake")
    public Authority Remake(@RequestBody String remakeAuthority) {
        Gson gson = new Gson();
        Authority authority = gson.fromJson(remakeAuthority, Authority.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Authority>> violation = validator.validate(authority);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return authorityServices.AddNew(authority);
        }
        return null;
    }

    @DeleteMapping(value = "authority/delete")
    public Authority Delete(@RequestParam int authorityId) {
        return authorityServices.Delete(authorityId);
    }

    @GetMapping(value = "authority/get")
    public List<Authority> GetAll() {
        return authorityServices.GetAll();
    }

    @GetMapping(value = "authority/getByPage")
    public Page<Authority> GetByPage(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 1;
        Pageable pageable = PageRequest.of(page, size);
        return authorityServices.GetByPage(pageable);
    }
}