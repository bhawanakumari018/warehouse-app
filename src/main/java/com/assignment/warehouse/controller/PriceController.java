package com.assignment.warehouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.warehouse.entity.Price;
import com.assignment.warehouse.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/{id}")
    public Price getPriceById(@PathVariable String id) {
        return priceService.getPriceById(id);
    }

    @PostMapping
    public Price createPrice(@RequestBody Price price) {
        return priceService.createPrice(price);
    }

    @PutMapping("/{id}")
    public Price updatePrice(@PathVariable String id,
                             @RequestBody Price price) {
        price.setArticleId(id);
        return priceService.updatePrice(price);
    }

    @DeleteMapping("/{id}")
    public void deletePrice(@PathVariable String id) {
        priceService.deletePrice(id);
    }
}