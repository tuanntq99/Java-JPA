package TestJPA.Services;

import TestJPA.Entity.Post;
import TestJPA.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServices implements PostInterfaceServices {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post AddNew(Post newPost) {
        java.util.Date utilDate = java.util.Date.from(Instant.now());
        System.out.println("utilDate: " + utilDate);
        if (utilDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(utilDate);
            System.out.println("formattedDate: " + formattedDate);
            newPost.setCreateTime(formattedDate);
            postRepository.save(newPost);
        }
        return newPost;
    }

    @Override
    public Post Remake(Post remakePost) {
        Optional<Post> post = postRepository.findById(remakePost.getId());
        if (post.isEmpty()) {
            System.out.println("remakePost is null");
            return null;
        }
        java.util.Date utilDate = java.util.Date.from(Instant.now());
        System.out.println("utilDate: " + utilDate);
        if (utilDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(utilDate);
            System.out.println("formattedDate: " + formattedDate);
            remakePost.setCreateTime(formattedDate);
            postRepository.save(remakePost);
        }
        return remakePost;
    }

    @Override
    public Post Delete(int postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            System.out.println("postId is null");
            return null;
        }
        postRepository.delete(post.get());
        return post.get();
    }

    @Override
    public List<Post> GetAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> GetByName(String postName) {
        List<Post> lst = new ArrayList<>();
        postRepository.findAll().forEach(x -> {
            if (x.getPostName().equals(postName))
                lst.add(x);
        });
        return lst;
    }

    @Override
    public Page<Post> GetByPage(Pageable page) {
        return postRepository.findAll(page);
    }
}
