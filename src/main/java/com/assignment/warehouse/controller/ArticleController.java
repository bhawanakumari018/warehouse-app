package com.assignment.warehouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.warehouse.entity.Article;
import com.assignment.warehouse.service.ArticleService;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }
    
    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable String id) {
        return articleService.getArticleById(id);
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable String id,
                                 @RequestBody Article article) {

        article.setArticleId(id);
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
    }
}