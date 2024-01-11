package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class ArticlePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(max = 50, message = "Max size of articlePostName is less than 51")
    private String articlePostName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "articlePost")
    @JsonIgnoreProperties(value = "articlePost")
    private List<Topic> topics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticlePostName() {
        return articlePostName;
    }

    public void setArticlePostName(String articlePostName) {
        this.articlePostName = articlePostName;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
