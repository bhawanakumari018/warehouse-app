package com.assignment.warehouse.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    private String articleId;

    private BigDecimal price;

    public Price() {
    }

    public Price(String articleId, BigDecimal price) {
        this.articleId = articleId;
        this.price = price;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}