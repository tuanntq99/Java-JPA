package TestJPA.Services;

import TestJPA.Entity.Course;
import TestJPA.Entity.Register;
import TestJPA.Entity.Status;
import TestJPA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterServices implements RegisterInterfaceServices {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private CourseServices courseServices;

    @Override
    public String DateInstance(java.util.Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(time);
//        System.out.println("formattedDate: " + formattedDate);
        return formattedDate;
    }

    @Override
    public void RegisterUpdateTime(Register register) {
        Optional<Register> listRegister = registerRepository.findById(register.getId());
        if (listRegister.isEmpty()) {
            System.out.println("register is null");
        }
        java.util.Date utilDate = java.util.Date.from(Instant.now());
//        System.out.println("utilDate: " + utilDate);
        System.out.println("new coursesId out: " + register.getCourses().getId());
        Register newReg = register;

//        int studyTime = register.get().getCourses().getStudyTime();
        int studyTime = courseRepository.findById(register.getCourses().getId()).get().getStudyTime();
        String registerDay = newReg.getRegisterDay();
        if (registerDay == null) {
            registerDay = DateInstance(utilDate);
//            studyTime = courseRepository.findById(register.get().getCourses().getId()).get().getStudyTime();
        }
        System.out.println("registerDay: " + registerDay);
        System.out.println("studyTime: " + studyTime);

        String dayBegin = newReg.getDayBegin();
        System.out.println("dayBegin current: " + dayBegin);

        String dayEnd = newReg.getDayEnd();
        System.out.println("dayEnd current: " + dayEnd);

        for (Status x : statusRepository.findAll()) {
            if (newReg.getStatus().getId() == 2) {
                if (dayBegin == null) {
                    dayBegin = DateInstance(utilDate);
//                    System.out.println("dayBegin.now(): " + dayBegin);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(utilDate);
                    calendar.add(Calendar.HOUR_OF_DAY, studyTime);

                    java.util.Date dayEndUtil = calendar.getTime();
//                    System.out.println("dayEndUtil: " + dayEndUtil);
                    dayEnd = DateInstance(dayEndUtil);
                    System.out.println("dayBegin result: " + dayBegin);
                    System.out.println("dayEnd result: " + dayEnd);
                    break;
                } else {
//                    dayBegin = newReg.getDayBegin(); // if do not want change dayBegin
                    dayBegin = DateInstance(utilDate); // if need change dayBegin when tranfser to newCourse
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(utilDate);
                    calendar.add(Calendar.HOUR_OF_DAY, studyTime);

                    java.util.Date dayEndUtil = calendar.getTime();
//                    System.out.println("dayEndUtil: " + dayEndUtil);
                    dayEnd = DateInstance(dayEndUtil);
                    System.out.println("dayBegin result: " + dayBegin);
                    System.out.println("dayEnd result: " + dayEnd);
                    break;
                }
            }
        }
        newReg.setRegisterDay(registerDay);
        newReg.setDayBegin(dayBegin);
        newReg.setDayEnd(dayEnd);
        registerRepository.save(newReg);
    }

    @Override
    public boolean CheckDataExist() {
        if (accountRepository.count() > 0) return false;
        if (traineeRepository.count() > 0) return false;
        if (courseRepository.count() > 0) return false;
        if (statusRepository.count() > 0) return false;
        System.out.println("Other class data is not null");
        return true;
    }

    @Override
    public Register AddNew(Register newRegister) {
        CheckDataExist(); // Check before add new register
        registerRepository.save(newRegister);
        RegisterUpdateTime(newRegister);
        registerRepository.save(newRegister);
        courseServices.UpdateNumberOfTrainee(newRegister.getCourses().getId());
        return newRegister;
    }

    @Override
    public Register Remake(Register remakeRegister) {
        Optional<Register> register = registerRepository.findById(remakeRegister.getId()); // get id
        if (register.isEmpty()) {
            System.out.println("remakeId is null");
            return null;
        }
        System.out.println("remake id: " + remakeRegister.getId());
        System.out.println("old courseId in: " + register.get().getCourses().getId());
        Register current = register.get(); // old object
        int oldId = current.getCourses().getId(); // id of old object
        RegisterUpdateTime(remakeRegister);
//        return remakeRegister;
        System.out.println("old courseId: " + oldId);
        System.out.println("new courseId: " + remakeRegister.getCourses().getId());
        if (remakeRegister.getRegisterDay() == null) {
            remakeRegister.setRegisterDay(current.getRegisterDay());
        }
        if (remakeRegister.getTrainee() == null) {
            remakeRegister.setTrainee(current.getTrainee());
        }
        if (remakeRegister.getDayBegin() == null) {
            remakeRegister.setDayBegin(current.getDayBegin());
        }
        if (remakeRegister.getDayEnd() == null) {
            remakeRegister.setDayEnd(current.getDayEnd());
        }
        if (remakeRegister.getAccounts() == null) {
            remakeRegister.setAccounts(current.getAccounts());
        }
        if (remakeRegister.getStatus() == null) {
            remakeRegister.setStatus(current.getStatus());
        }
        if (remakeRegister.getCourses() == null) {
            remakeRegister.setCourses(current.getCourses());
        }
        current = remakeRegister; // set new object
        registerRepository.save(current);
        courseServices.UpdateNumberOfTrainee(remakeRegister.getCourses().getId()); // update new id and number with new object
        courseServices.UpdateNumberOfTrainee(oldId);  // update old id and number
        return current;
    }

    @Override
    public Register Delete(int registerId) {
        Optional<Register> register = registerRepository.findById(registerId);
        if (register.isEmpty()) {
            System.out.println("registerId is null");
            return null;
        }
        registerRepository.delete(register.get());
        return register.get();
    }

    @Override
    public List<Register> GetAll() {
        return registerRepository.findAll();
    }

    @Override
    public Page<Register> GetByPage(Pageable page) {
        return registerRepository.findAll(page);
    }
}
