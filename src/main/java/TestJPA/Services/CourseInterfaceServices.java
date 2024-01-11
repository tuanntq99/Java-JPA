package TestJPA.Services;

import TestJPA.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseInterfaceServices {
    public Course AddNew(Course newCourse);

    public Course Remake(Course remakeCourse);

    public Course Delete(int courseId);

    public List<Course> GetAllCourse();

    public List<Course> GetByName(String courseName);

    public Page<Course> GetByPage(Pageable page);

    public void UpdateNumberOfTrainee(int courseId);
}
