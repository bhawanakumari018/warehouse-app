package com.assignment.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.warehouse.entity.Article;
import com.assignment.warehouse.exception.ResourceNotFoundException;
import com.assignment.warehouse.repository.ArticleRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
    
    public Article getArticleById(String id) {

        return articleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Article not found"));
    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }
}