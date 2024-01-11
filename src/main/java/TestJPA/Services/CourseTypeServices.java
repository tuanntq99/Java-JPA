package TestJPA.Services;

import TestJPA.Entity.CourseType;
import TestJPA.Repository.CourseRepository;
import TestJPA.Repository.CourseTypeRepository;
import TestJPA.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTypeServices implements CourseTypeInterfaceServices {
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public CourseType AddNew(CourseType newCourseType) {
        courseTypeRepository.save(newCourseType);
        return newCourseType;
    }

    @Override
    public CourseType Remake(CourseType remakeCourseType) {
        Optional<CourseType> courseType = courseTypeRepository.findById(remakeCourseType.getId());
        if (courseType.isEmpty()) {
            System.out.println("fail");
            return null;
        }
        CourseType current = courseType.get();
        current = remakeCourseType;
        courseTypeRepository.save(current);
        return current;
    }

    @Override
    public CourseType Delete(int courseTypeId) {
        Optional<CourseType> type = courseTypeRepository.findById(courseTypeId);
        if (type.isEmpty()) {
            return null;
        } else {
            courseRepository.findAll().forEach(x -> {
                if (x.getCourseType().getId() == courseTypeId)
                    registerRepository.findAll().forEach(y -> {
                        if (y.getCourses() == x)
                            registerRepository.delete(y);
                    });
                courseRepository.delete(x);
            });
            courseTypeRepository.delete(type.get());
        }
        return type.get();
    }
}
