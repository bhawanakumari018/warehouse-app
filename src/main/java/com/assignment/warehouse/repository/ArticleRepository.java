package com.assignment.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.warehouse.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {

}