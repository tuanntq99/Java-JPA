package TestJPA.Controller;

import TestJPA.Entity.Post;
import TestJPA.Services.PostServices;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class PostController {
    @Autowired
    private PostServices postServices;

    @PostMapping(value = "post/add")
    public Post AddNew(@RequestBody String newPost) {
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
//            @Override
//            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
//            }
//        }).create();
//        Gson gson = new GsonBuilder().setDateFormat("yy-MM-dd HH:mm:ss").create();
        Gson gson = new Gson();
        Post post = gson.fromJson(newPost, Post.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Post>> violation = validator.validate(post);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return postServices.AddNew(post);
        }
        return null;
    }

    @PutMapping(value = "post/remake")
    public Post Remake(@RequestBody String remakePost) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(JsonWriter out, LocalDateTime value) throws IOException {
                        out.value(value.toString());
                    }

                    @Override
                    public LocalDateTime read(JsonReader in) throws IOException {
                        return LocalDateTime.parse(in.nextString());
                    }
                })
                .create();

        Post post = gson.fromJson(remakePost, Post.class);

        ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
        Validator validator = valfac.getValidator();
        Set<ConstraintViolation<Post>> violation = validator.validate(post);
        violation.forEach(x -> {
            System.out.println(x.getMessage());
        });

        if (violation.size() == 0) {
            return postServices.Remake(post);
        }
        return null;
    }

    @DeleteMapping(value = "post/delete")
    public Post Delete(@RequestParam int postId) {
        return postServices.Delete(postId);
    }

    @GetMapping(value = "post/get")
    public List<Post> GetAll() {
        return postServices.GetAll();
    }

    @GetMapping(value = "post/getByName")
    public List<Post> GetByName(@RequestParam String postName) {
        return postServices.GetByName(postName);
    }

    @GetMapping(value = "post/getByPage")
    public Page<Post> GetByPage(Pageable page) {
        return postServices.GetByPage(page);
    }

}
