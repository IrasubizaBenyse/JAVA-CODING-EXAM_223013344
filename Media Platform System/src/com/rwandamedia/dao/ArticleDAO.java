package com.rwandamedia.dao;

import com.rwandamedia.system.InMemoryDatabase;
import com.rwandamedia.models.Article;
import java.util.List;

public class ArticleDAO {
    
    public List<Article> getAllArticles() {
        return InMemoryDatabase.getAllArticles();
    }
    
    public boolean addArticle(Article article) {
        System.out.println("Article added: " + article.getTitle());
        return true;
    }
    
    public boolean updateArticle(Article article) {
        System.out.println("Article updated: " + article.getTitle());
        return true;
    }
    
    public boolean deleteArticle(int articleID) {
        System.out.println("Article deleted: " + articleID);
        return true;
    }
    
    public Article getArticleById(int articleID) {
        List<Article> articles = getAllArticles();
        for (Article article : articles) {
            if (article.getArticleID() == articleID) {
                return article;
            }
        }
        return null;
    }
}