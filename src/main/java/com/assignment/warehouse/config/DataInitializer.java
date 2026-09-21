package com.assignment.warehouse.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.warehouse.entity.Article;
import com.assignment.warehouse.entity.Price;
import com.assignment.warehouse.entity.Product;
import com.assignment.warehouse.entity.ProductArticle;
import com.assignment.warehouse.repository.ArticleRepository;
import com.assignment.warehouse.repository.PriceRepository;
import com.assignment.warehouse.repository.ProductArticleRepository;
import com.assignment.warehouse.repository.ProductRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            ArticleRepository articleRepository,
            PriceRepository priceRepository,
            ProductRepository productRepository,
            ProductArticleRepository productArticleRepository) {

        return args -> {

        	// Articles
        	Article leg = new Article("A1", "Leg", 100);
        	Article board = new Article("A2", "Board", 50);

        	articleRepository.save(leg);
        	articleRepository.save(board);

        	// Prices
        	priceRepository.save(
        	        new Price("A1", BigDecimal.valueOf(10)));

        	priceRepository.save(
        	        new Price("A2", BigDecimal.valueOf(20)));

        	// Product
        	Product table = new Product("P1", "Table");

        	productRepository.save(table);

        	// ProductArticle 1
        	ProductArticle pa1 = new ProductArticle();
        	pa1.setProduct(table);
        	pa1.setArticle(leg);
        	pa1.setQuantityRequired(4);

        	productArticleRepository.save(pa1);

        	// ProductArticle 2
        	ProductArticle pa2 = new ProductArticle();
        	pa2.setProduct(table);
        	pa2.setArticle(board);
        	pa2.setQuantityRequired(1);

        	productArticleRepository.save(pa2);
        };
    }
}      