package TestJPA.Services;

import TestJPA.Entity.Trainee;
import TestJPA.Repository.RegisterRepository;
import TestJPA.Repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeServices implements TraineeInterfaceServices {
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private CourseServices courseServices;

    @Override
    public Trainee AddNew(Trainee newTrainee) {
        newTrainee.setFullName(formatName(newTrainee.getFullName()));
        traineeRepository.save(newTrainee);
        if (newTrainee.getRegisters() == null) {
            System.out.println("register is null");
            return newTrainee;
        }
        newTrainee.getRegisters().forEach(x -> {
            x.setTrainee(newTrainee);
            registerRepository.save(x);
        });
        System.out.println("trainee is not null");
        return newTrainee;
    }

    @Override
    public Trainee Remake(Trainee remakeTrainee) {
        Optional<Trainee> trainee = traineeRepository.findById(remakeTrainee.getId());
        if (trainee.isEmpty()) {
            System.out.println("fail");
            return null;
        }
        remakeTrainee.setFullName(formatName(remakeTrainee.getFullName()));
        Trainee current = trainee.get();
        current = remakeTrainee;
        traineeRepository.save(current);
        System.out.println("success");
        return current;
    }

    @Override
    public Trainee Delete(int traineeId) {
        Optional<Trainee> trainee = traineeRepository.findById(traineeId);
        if (trainee.isEmpty()) {
            System.out.println("fail");
            return null;
        } else {
            registerRepository.findAll().forEach(x -> {
                if (x.getTrainee().getId() == traineeId)
                    registerRepository.delete(x);
            });
        }
        traineeRepository.delete(trainee.get());
        return trainee.get();
    }

    @Override
    public List<Trainee> GetBy(String fullName, String email) {
        List<Trainee> lst = new ArrayList<>();
        traineeRepository.findAll().forEach(x -> {
            if (x.getFullName().toLowerCase().contains(fullName.toLowerCase()))
                if (x.getEmail().equals(email))
                    lst.add(x);
        });
        return lst;
    }

    @Override
    public String formatName(String fullName) {
        fullName = fullName.replace("  ", " ");
        fullName = fullName.trim();
        if (fullName == null || fullName.isEmpty()) {
            return fullName;
        }
        String words[] = fullName.split(" ");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstLetter = Character.toUpperCase(word.charAt(0));
                String restOfWord = word.substring(1).toLowerCase();
                formattedName.append(firstLetter).append(restOfWord).append(" ");
            }
        }
        return formattedName.toString().trim();
    }
}
