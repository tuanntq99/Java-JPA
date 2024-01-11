package TestJPA.Services;

import TestJPA.Entity.Trainee;

import java.util.List;

public interface TraineeInterfaceServices {
    public Trainee AddNew(Trainee newTrainee);

    public Trainee Remake(Trainee remakeTrainee);

    public Trainee Delete(int traineeId);

    public List<Trainee> GetBy(String fullName, String email);

    public String formatName(String fullName);
}
