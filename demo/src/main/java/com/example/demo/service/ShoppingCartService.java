package com.example.demo.service;

import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> findShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @Transactional
    public Optional<ShoppingCart> updateShoppingCart(Long id, ShoppingCart updatedShoppingCart) {
        return shoppingCartRepository.findById(id).map(existingCart -> {
            existingCart.setUser(updatedShoppingCart.getUser());
            existingCart.setProducts(updatedShoppingCart.getProducts());
            existingCart.setTotalPrice(updatedShoppingCart.getTotalPrice());
            return shoppingCartRepository.save(existingCart);
        });
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
