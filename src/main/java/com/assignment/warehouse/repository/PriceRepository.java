package com.assignment.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.warehouse.entity.Price;

public interface PriceRepository extends JpaRepository<Price, String> {
}