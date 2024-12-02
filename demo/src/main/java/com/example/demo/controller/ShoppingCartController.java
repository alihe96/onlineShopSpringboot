package com.example.demo.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Create a new shopping cart
    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);
        return ResponseEntity.ok(savedCart);
    }

    // Get all shopping carts
    @GetMapping
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartService.findAllShoppingCarts();
    }

    // Get a shopping cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findShoppingCartById(id);
        return shoppingCart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a shopping cart by ID
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCart updatedShoppingCart) {
        Optional<ShoppingCart> updated = shoppingCartService.updateShoppingCart(id, updatedShoppingCart);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a shopping cart by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.noContent().build();
    }
}
