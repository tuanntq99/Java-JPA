package TestJPA.Controller;

import TestJPA.Entity.ArticlePost;
import TestJPA.Entity.Topic;
import TestJPA.Services.TopicServices;
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
public class TopicController {
    @Autowired
    private TopicServices topicServices;

    @PostMapping(value = "topic/add")
    public Topic AddNew(@RequestBody String newTopic) {
        Gson gson = new Gson();
        Topic topic = gson.fromJson(newTopic, Topic.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Topic>> violation = validator.validate(topic);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return topicServices.AddNew(topic);
        }
        return null;
    }

    @PutMapping(value = "topic/remake")
    public Topic Remake(@RequestBody String remakeTopic) {
        Gson gson = new Gson();
        Topic topic = gson.fromJson(remakeTopic, Topic.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Topic>> violation = validator.validate(topic);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return topicServices.Remake(topic);
        }
        return null;
    }

    @DeleteMapping(value = "topic/delete")
    public Topic Delete(@RequestParam int topicId) {
        return topicServices.Delete(topicId);
    }

    @GetMapping(value = "topic/get")
    public List<Topic> GetAll() {
        return topicServices.GetAll();
    }

    @GetMapping(value = "topic/getByPage")
    public Page<Topic> GetByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return topicServices.GetByPage(pageable);
    }

}
