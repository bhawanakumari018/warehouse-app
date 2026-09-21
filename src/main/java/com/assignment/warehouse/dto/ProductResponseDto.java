package com.assignment.warehouse.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponseDto {

    private String prodId;

    private String productName;

    private List<ArticleResponseDto> articles;

    private BigDecimal price;

    public ProductResponseDto(
            String prodId,
            String productName,
            List<ArticleResponseDto> articles,
            BigDecimal price) {

        this.prodId = prodId;
        this.productName = productName;
        this.articles = articles;
        this.price = price;
    }

    public String getProdId() {
        return prodId;
    }

    public String getProductName() {
        return productName;
    }

    public List<ArticleResponseDto> getArticles() {
        return articles;
    }

    public BigDecimal getPrice() {
        return price;
    }
}