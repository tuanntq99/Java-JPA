package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(max = 50, message = "Max size of courseName is less than 51")
    private String courseName;

    @Column
    @NotNull
    private int studyTime;

    @Column
    @NotNull
    @Size(max = Integer.MAX_VALUE, message = "Max size of introduce is unlimited")
    private String introduce;

    @Column
    @NotNull
    @Size(max = Integer.MAX_VALUE, message = "Max size of content is unlimited")
    private String content;

    @Column
    @NotNull
    private float tuitionFee;

    @Column
    @NotNull
    private int numberOfTrainee;

    @Column
    @NotNull
    private int numberOfSubjects;

    @Column
//    @NotNull // check pls
    @Size(max = Integer.MAX_VALUE, message = "Max size of image is unlimited")
    private String image;

    @ManyToOne
    @JoinColumn(name = "course_type_id", foreignKey = @ForeignKey(name = "fk_Course_CourseType"))
    @JsonIgnoreProperties(value = "courses")
    private CourseType courseType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    @JsonIgnoreProperties(value = "courses")
    private List<Register> registers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public float getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(float tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public int getNumberOfTrainee() {
        return numberOfTrainee;
    }

    public void setNumberOfTrainee(int numberOfTrainee) {
        this.numberOfTrainee = numberOfTrainee;
    }

    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {

        this.registers = registers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
