package com.assignment.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.warehouse.entity.Product;
import com.assignment.warehouse.entity.ProductArticle;

public interface ProductArticleRepository
        extends JpaRepository<ProductArticle, Long> {
	
	//use this for price calculation

    List<ProductArticle> findByProduct(Product product);
}