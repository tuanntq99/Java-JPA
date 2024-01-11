package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(max = 50, message = "Max size of postName is less than 51")
    private String postName;

    @Column
//    @NotNull
    private String createTime;

    @Column
    @NotNull
    @Size(max = 50, message = "Max size of author is less than 51")
    private String author;

    @Column
    @NotNull
    @Size(max = Integer.MAX_VALUE, message = "Max size of content is unlimted")
    private String content;

    @Column
    @NotNull
    @Size(max = 1000, message = "Max size of shortContent is less than 1001")
    private String shortContent;

    @Column
    @NotNull
    @Size(max = Integer.MAX_VALUE, message = "Max size of image is unlimited")
    private String image;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_Post_Account"))
    @JsonIgnoreProperties(value = "posts")
    private Account accounts;

    @ManyToOne
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_Post_Topic"))
    @JsonIgnoreProperties(value = "posts")
    private Topic topics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public Topic getTopics() {
        return topics;
    }

    public void setTopics(Topic topics) {
        this.topics = topics;
    }
}
