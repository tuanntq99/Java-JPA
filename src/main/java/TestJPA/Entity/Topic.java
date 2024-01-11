package TestJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Topic {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(max = 50, message = "Max size of topicName is less than 51")
    private String topicName;

    @Column
    @NotNull
    @Size(max = Integer.MAX_VALUE, message = "Max size of content is unlimited")
    private String contents;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "topics")
    @JsonIgnoreProperties(value = "topics")
    private List<Post> posts;

    @ManyToOne
    @JoinColumn(name = "article_post_id", foreignKey = @ForeignKey(name = "fk_Topic_ArticlePost"))
    @JsonIgnoreProperties(value = "topics")
    private ArticlePost articlePost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getContent() {
        return contents;
    }

    public void setContent(String content) {
        this.contents = content;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public ArticlePost getArticlePost() {
        return articlePost;
    }

    public void setArticlePost(ArticlePost articlePost) {
        this.articlePost = articlePost;
    }
}
