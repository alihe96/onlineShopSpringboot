package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.ok(savedOrderItem);
    }

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // Get order item by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update order item by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        Optional<OrderItem> updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDetails);
        return updatedOrderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete order item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    // Delete all order items
    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrderItems() {
        orderItemService.deleteAllOrderItems();
        return ResponseEntity.noContent().build();
    }
}
