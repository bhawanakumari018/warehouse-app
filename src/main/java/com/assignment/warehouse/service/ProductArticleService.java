package com.assignment.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.warehouse.entity.ProductArticle;
import com.assignment.warehouse.exception.ResourceNotFoundException;
import com.assignment.warehouse.repository.ProductArticleRepository;

@Service
public class ProductArticleService {

    private final ProductArticleRepository productArticleRepository;

    public ProductArticleService(
            ProductArticleRepository productArticleRepository) {

        this.productArticleRepository = productArticleRepository;
    }

    public List<ProductArticle> getAll() {
        return productArticleRepository.findAll();
    }

    public ProductArticle getById(Long id) {
        return productArticleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ProductArticle not found"));
    }

    public ProductArticle save(ProductArticle productArticle) {
        return productArticleRepository.save(productArticle);
    }

    public ProductArticle update(Long id,
                                 ProductArticle productArticle) {

        ProductArticle existing = getById(id);

        existing.setProduct(productArticle.getProduct());
        existing.setArticle(productArticle.getArticle());
        existing.setQuantityRequired(
                productArticle.getQuantityRequired());

        return productArticleRepository.save(existing);
    }

    public void delete(Long id) {
        productArticleRepository.deleteById(id);
    }
}