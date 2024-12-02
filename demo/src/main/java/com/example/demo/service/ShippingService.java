package com.example.demo.service;

import com.example.demo.model.Shipping;
import com.example.demo.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    @Autowired
    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public Shipping saveShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    public Optional<Shipping> findShippingById(Long id) {
        return shippingRepository.findById(id);
    }

    public List<Shipping> findAllShippings() {
        return shippingRepository.findAll();
    }

    @Transactional
    public Optional<Shipping> updateShipping(Long id, Shipping updatedShipping) {
        return shippingRepository.findById(id).map(existingShipping -> {
            existingShipping.setOrder(updatedShipping.getOrder());
            existingShipping.setShippingDate(updatedShipping.getShippingDate());
            existingShipping.setShippingMethod(updatedShipping.getShippingMethod());
            existingShipping.setShippingCost(updatedShipping.getShippingCost());
            existingShipping.setTrackingNumber(updatedShipping.getTrackingNumber());
            existingShipping.setShippingAddress(updatedShipping.getShippingAddress());
            existingShipping.setEstimatedDeliveryDate(updatedShipping.getEstimatedDeliveryDate());
            existingShipping.setActualDeliveryDate(updatedShipping.getActualDeliveryDate());
            return shippingRepository.save(existingShipping);
        });
    }

    public void deleteShipping(Long id) {
        shippingRepository.deleteById(id);
    }
}
