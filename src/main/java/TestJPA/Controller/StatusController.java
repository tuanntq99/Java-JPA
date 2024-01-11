package TestJPA.Controller;

import TestJPA.Entity.Status;
import TestJPA.Services.StatusServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "api/version1.0")
public class StatusController {
    @Autowired
    private StatusServices statusServices;

    @PostMapping(value = "status/add")
    public Status AddNew(@RequestBody String newStatus) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Status status = gson.fromJson(newStatus, Status.class);
        return statusServices.AddNew(status);
    }

    @PutMapping(value = "status/remake")
    public Status Remake(@RequestBody String remakeStatus) {
        Gson gson = new Gson();
        Status status = gson.fromJson(remakeStatus, Status.class);
        return statusServices.Remake(status);
    }

    @DeleteMapping(value = "status/delete")
    public Status Delete(@RequestParam int statusId) {
        return statusServices.Delete(statusId);
    }

    @GetMapping(value = "status/get")
    public List<Status> GetAll() {
        return statusServices.GetAll();
    }
}
