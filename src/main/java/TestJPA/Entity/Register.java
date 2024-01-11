package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String RegisterDay;

    @Column
    private String dayBegin;

    @Column
    private String dayEnd;

    @ManyToOne
    @JoinColumn(name = "trainee_id", foreignKey = @ForeignKey(name = "fk_Register_Trainee"))
    @JsonIgnoreProperties(value = "registers")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "Course_id", foreignKey = @ForeignKey(name = "fk_Register_Course"))
    @JsonIgnoreProperties(value = "registers")
    private Course courses;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_Register_Account"))
    @JsonIgnoreProperties(value = "registers")
    private Account accounts;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(name = "fk_Register_Status"))
    @JsonIgnoreProperties(value = "registers")
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegisterDay() {
        return RegisterDay;
    }

    public void setRegisterDay(String registerDay) {
        RegisterDay = registerDay;
    }

    public String getDayBegin() {
        return dayBegin;
    }

    public void setDayBegin(String dayBegin) {
        this.dayBegin = dayBegin;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
