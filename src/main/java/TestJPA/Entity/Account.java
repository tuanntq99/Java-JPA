package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(max = 50, message = "Max size of userName is less than 51")
    private String userName;

    @Column(unique = true)
    @Size(max = 50, message = "Max size of account is less than 51")
    private String account;

    @Column
    @Pattern.List({
            @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit"),
            @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", message = "Password must contain at least one special character")
    })
    @Size(max = 50, message = "Max size of password is less than 51")
    private String password;

    @ManyToOne
    @JoinColumn(name = "authority_id", foreignKey = @ForeignKey(name = "fk_Account_Authority"))
    @JsonIgnoreProperties(value = "accounts")
    private Authority authorities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
    @JsonIgnoreProperties(value = "accounts")
    private List<Register> registers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
    @JsonIgnoreProperties(value = "accounts")
    private List<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authority authorities) {
        this.authorities = authorities;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
