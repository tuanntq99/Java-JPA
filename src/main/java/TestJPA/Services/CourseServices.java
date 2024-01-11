package TestJPA.Services;

import TestJPA.Entity.Course;
import TestJPA.Entity.Register;
import TestJPA.Repository.CourseRepository;
import TestJPA.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServices implements CourseInterfaceServices {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Course AddNew(Course newCourse) {
        courseRepository.save(newCourse);
        return newCourse;
    }

    @Override
    public Course Remake(Course remakeCourse) {
        Optional<Course> course = courseRepository.findById(remakeCourse.getId());
        if (course.isEmpty()) {
            System.out.println("fail");
            return null;
        }
        Course currentCourse = course.get();
        currentCourse = remakeCourse;
        courseRepository.save(currentCourse);
        return currentCourse;
    }

    @Override
    public Course Delete(int courseId) {
        Optional<Course> delcourse = courseRepository.findById(courseId);
        if (delcourse.isEmpty()) {
            return null;
        } else {
            registerRepository.findAll().forEach(x -> {
                if (x.getCourses().getId() == courseId) {
                    registerRepository.delete(x);
                }
            });
        }
        courseRepository.delete(delcourse.get());
        return delcourse.get();
    }

    @Override
    public List<Course> GetAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> GetByName(String courseName) {
        List<Course> lst = new ArrayList<>();
        courseRepository.findAll().forEach(x -> {
            if (x.getCourseName().toLowerCase().contains(courseName.toLowerCase()))
                lst.add(x);
        });
        return lst;
    }

    @Override
    public Page<Course> GetByPage(Pageable page) {
        return courseRepository.findAll(page);
    }

    @Override
    public void UpdateNumberOfTrainee(int courseId) {
        Course course = courseRepository.findById(courseId).get();
        int quantity = 0;
        for (Register x : registerRepository.findAll()) {
            if (x.getCourses().getId() == courseId)
                quantity++;
        }
        course.setNumberOfTrainee(quantity);
        courseRepository.save(course);
    }
}
