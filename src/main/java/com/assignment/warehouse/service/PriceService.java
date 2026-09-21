package com.assignment.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.warehouse.entity.Price;
import com.assignment.warehouse.exception.ResourceNotFoundException;
import com.assignment.warehouse.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Price getPriceById(String id) {

        return priceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Price not found"));
    }

    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

    public Price updatePrice(Price price) {
        return priceRepository.save(price);
    }

    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }
}