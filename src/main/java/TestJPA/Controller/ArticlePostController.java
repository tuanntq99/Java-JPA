package TestJPA.Controller;

import TestJPA.Entity.Account;
import TestJPA.Entity.ArticlePost;
import TestJPA.Services.ArticlePostServices;
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
public class ArticlePostController {
    @Autowired
    private ArticlePostServices articlePostServices;

    @PostMapping(value = "articlePost/add")
    public ArticlePost AddNew(@RequestBody String newArticlePost) {
        Gson gson = new Gson();
        ArticlePost articlePost = gson.fromJson(newArticlePost, ArticlePost.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<ArticlePost>> violation = validator.validate(articlePost);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return articlePostServices.AddNew(articlePost);
        }
        return null;
    }

    @PutMapping(value = "articlePost/remake")
    public ArticlePost Remake(@RequestBody String remakeArticlePost) {
        Gson gson = new Gson();
        ArticlePost articlePost = gson.fromJson(remakeArticlePost, ArticlePost.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<ArticlePost>> violation = validator.validate(articlePost);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return articlePostServices.Remake(articlePost);
        }
        return null;
    }

    @DeleteMapping(value = "articlePost/delete")
    public ArticlePost Delete(@RequestParam int articlePostId) {
        return articlePostServices.Delete(articlePostId);
    }

    @GetMapping(value = "articlePost/get")
    public List<ArticlePost> GetAll() {
        return articlePostServices.GetAll();
    }

    @GetMapping(value = "articlePost/getByPage")
    public Page<ArticlePost> GetByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return articlePostServices.GetByPage(pageable);
    }

}
