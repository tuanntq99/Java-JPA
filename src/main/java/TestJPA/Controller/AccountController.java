package TestJPA.Controller;

import TestJPA.Entity.Account;
import TestJPA.Entity.Authority;
import TestJPA.Services.AccountServices;
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
public class AccountController {
    @Autowired
    private AccountServices accountServices;

    @PostMapping(value = "account/add")
    public Account AddNew(@RequestBody String newAccount) {
        Gson gson = new Gson();
        Account account = gson.fromJson(newAccount, Account.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Account>> violation = validator.validate(account);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return accountServices.AddNew(account);
        }
        return null;
    }

    @PutMapping(value = "account/remake")
    public Account Remake(@RequestBody String remakeAccount) {
        Gson gson = new Gson();
        Account account = gson.fromJson(remakeAccount, Account.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Account>> violation = validator.validate(account);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return accountServices.Remake(account);
        }
        return null;
    }

    @DeleteMapping(value = "account/delete")
    public Account Delete(@RequestParam int accountId) {
        return accountServices.Delete(accountId);
    }

    @GetMapping(value = "account/get")
    public List<Account> GetAll() {
        return accountServices.GetAll();
    }

    @GetMapping(value = "account/getByName")
    public List<Account> GetByName(@RequestParam String accountName) {
        return accountServices.GetByName(accountName);
    }

    @GetMapping(value = "account/getByPage")
    public Page<Account> GetByPage(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 1;
        Pageable pageable = PageRequest.of(page, size);
        return accountServices.GetByPage(pageable);
    }
}
