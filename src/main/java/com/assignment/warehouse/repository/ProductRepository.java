package com.assignment.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.warehouse.entity.Product;

public interface ProductRepository
        extends JpaRepository<Product, String> {

}