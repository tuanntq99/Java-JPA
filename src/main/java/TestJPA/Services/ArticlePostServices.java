package TestJPA.Services;

import TestJPA.Entity.ArticlePost;
import TestJPA.Repository.ArticlePostRepository;
import TestJPA.Repository.PostRepository;
import TestJPA.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlePostServices implements ArticlePostInterfaceServices {
    @Autowired
    private ArticlePostRepository articlePostRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public ArticlePost AddNew(ArticlePost newArticlePost) {
        articlePostRepository.save(newArticlePost);
        return newArticlePost;
    }

    @Override
    public ArticlePost Remake(ArticlePost remakeArticlePost) {
        Optional<ArticlePost> articlePost = articlePostRepository.findById(remakeArticlePost.getId());
        if (articlePost.isEmpty()) {
            System.out.println("remakeArticlePost is null");
            return null;
        }
        ArticlePost current = articlePost.get();
        current = remakeArticlePost;
        articlePostRepository.save(current);
        return current;
    }

    @Override
    public ArticlePost Delete(int articlePostId) {
        Optional<ArticlePost> articlePost = articlePostRepository.findById(articlePostId);
        if (articlePost.isEmpty()) {
            System.out.println("articlePostId is null");
            return null;
        }
        topicRepository.findAll().forEach(x -> {
            if (x.getArticlePost().getId() == articlePostId)
                postRepository.findAll().forEach(y -> {
                    if (y.getTopics() == x)
                        postRepository.delete(y);
                });
            topicRepository.delete(x);
        });
        articlePostRepository.delete(articlePost.get());
        return articlePost.get();
    }

    @Override
    public List<ArticlePost> GetAll() {
        return articlePostRepository.findAll();
    }

    @Override
    public Page<ArticlePost> GetByPage(Pageable page) {
        return articlePostRepository.findAll(page);
    }
}
