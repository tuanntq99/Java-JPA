package TestJPA.Services;

import TestJPA.Entity.ArticlePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticlePostInterfaceServices {
    public ArticlePost AddNew(ArticlePost newArticlePost);

    public ArticlePost Remake(ArticlePost remakeArticlePost);

    public ArticlePost Delete(int articlePostId);

    public List<ArticlePost> GetAll();

    public Page<ArticlePost> GetByPage(Pageable page);
}
