package com.assignment.warehouse.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assignment.warehouse.dto.ArticleResponseDto;
import com.assignment.warehouse.dto.ProductResponseDto;
import com.assignment.warehouse.entity.Price;
import com.assignment.warehouse.entity.Product;
import com.assignment.warehouse.entity.ProductArticle;
import com.assignment.warehouse.exception.ResourceNotFoundException;
import com.assignment.warehouse.repository.PriceRepository;
import com.assignment.warehouse.repository.ProductArticleRepository;
import com.assignment.warehouse.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductArticleRepository productArticleRepository;
    private final PriceRepository priceRepository;

    public ProductService(
            ProductRepository productRepository,
            ProductArticleRepository productArticleRepository,
            PriceRepository priceRepository) {

        this.productRepository = productRepository;
        this.productArticleRepository = productArticleRepository;
        this.priceRepository = priceRepository;
    }

    public List<ProductResponseDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product ->
                        getProductResponse(
                                product.getProductId()))
                .collect(Collectors.toList());
    }

    public Product getProductById(String id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(
            String id,
            Product product) {

        Product existingProduct =
                getProductById(id);

        existingProduct.setProductName(
                product.getProductName());

        return productRepository.save(
                existingProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public BigDecimal calculateProductPrice(
            Product product) {

        List<ProductArticle> productArticles =
                productArticleRepository.findByProduct(product);

        BigDecimal total = BigDecimal.ZERO;

        for (ProductArticle pa : productArticles) {

            Price price = priceRepository
                    .findById(
                            pa.getArticle().getArticleId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Price not found"));

            BigDecimal articlePrice =
                    price.getPrice();

            total = total.add(
                    articlePrice.multiply(
                            BigDecimal.valueOf(
                                    pa.getQuantityRequired())));
        }

        return total;
    }

    public ProductResponseDto getProductResponse(String id) {

        Product product = getProductById(id);

        List<ProductArticle> productArticles =
                productArticleRepository.findByProduct(product);

        List<ArticleResponseDto> articles =
                productArticles.stream()
                        .map(pa -> new ArticleResponseDto(
                                pa.getArticle().getArticleId(),
                                pa.getArticle().getName(),
                                pa.getQuantityRequired()))
                        .collect(Collectors.toList());

        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                articles,
                calculateProductPrice(product));
    }
}