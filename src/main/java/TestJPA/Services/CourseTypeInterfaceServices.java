package TestJPA.Services;

import TestJPA.Entity.CourseType;

public interface CourseTypeInterfaceServices {
    public CourseType AddNew(CourseType newCourseType);

    public CourseType Remake(CourseType remakeCourseType);

    public CourseType Delete(int courseTypeId);
}
