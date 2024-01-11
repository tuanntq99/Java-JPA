package TestJPA.Repository;

import TestJPA.Entity.ArticlePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePostRepository extends JpaRepository<ArticlePost,Integer> {
}
