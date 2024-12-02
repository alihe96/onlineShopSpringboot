package com.example.demo.service;

import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public Optional<OrderItem> updateOrderItem(Long id, OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id).map(existingOrderItem -> {
            existingOrderItem.setProduct(updatedOrderItem.getProduct());
            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
            existingOrderItem.setPrice(updatedOrderItem.getPrice());
            existingOrderItem.setOrder(updatedOrderItem.getOrder());
            return orderItemRepository.save(existingOrderItem);
        });
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    public void deleteAllOrderItems() {
        orderItemRepository.deleteAll();
    }
}
