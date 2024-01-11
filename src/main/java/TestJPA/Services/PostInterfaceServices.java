package TestJPA.Services;

import TestJPA.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostInterfaceServices {
    public Post AddNew(Post newPost);

    public Post Remake(Post remakePost);

    public Post Delete(int postId);

    public List<Post> GetAll();

    public List<Post> GetByName(String postName);

    public Page<Post> GetByPage(Pageable page);
}
