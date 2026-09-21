package com.assignment.warehouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.assignment.warehouse.entity.ProductArticle;
import com.assignment.warehouse.service.ProductArticleService;

@RestController
@RequestMapping("/product-articles")
public class ProductArticleController {

    private final ProductArticleService productArticleService;

    public ProductArticleController(
            ProductArticleService productArticleService) {

        this.productArticleService = productArticleService;
    }

    @GetMapping
    public List<ProductArticle> getAll() {
        return productArticleService.getAll();
    }

    @GetMapping("/{id}")
    public ProductArticle getById(@PathVariable Long id) {
        return productArticleService.getById(id);
    }

    @PostMapping
    public ProductArticle createProductArticle(
            @RequestBody ProductArticle productArticle) {

        return productArticleService.save(productArticle);
    }

    @PutMapping("/{id}")
    public ProductArticle updateProductArticle(
            @PathVariable Long id,
            @RequestBody ProductArticle productArticle) {

        return productArticleService.update(id, productArticle);
    }

    @DeleteMapping("/{id}")
    public void deleteProductArticle(@PathVariable Long id) {
        productArticleService.delete(id);
    }
}