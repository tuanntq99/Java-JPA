package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(max = Integer.MAX_VALUE, message = "Max size of imageUrl is unlimted")
    private String imageUrl;

    @Column
    @Size(max = 50, message = "Max size of fullName is less than 51")
    private String fullName;

    @Column
    private Date dateOfBirth;

    @Column(unique = true)
    @Size(max = 11, message = "Max size of phoneNumber is less than 12")
    private String phoneNumber;

    @Column(unique = true)
    @Size(max = 40, message = "Max size of email is less than 41")
    private String email;

    @Column
    @Size(max = 50, message = "Max size of province is less than 51")
    private String province;

    @Column
    @Size(max = 50, message = "Max size of district is less than 51")
    private String district;

    @Column
    @Size(max = 50, message = "Max size of wards is less than 51")
    private String wards;

    @Column
    @Max(value = 50, message = "Max value of apartmentNo is less than 51")
    private int apartmentNo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainee")
    @JsonIgnoreProperties(value = "trainee")
    private List<Register> registers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String getFullName(String fullName) {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }
}
