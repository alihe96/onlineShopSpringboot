//package com.example.demo.test.service;
//
//import com.example.demo.model.ShoppingCart;
//import com.example.demo.model.User;
//import com.example.demo.model.Product;
//import com.example.demo.repository.ShoppingCartRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.ProductRepository;
//import com.example.demo.service.ShoppingCartService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ShoppingCartServiceTest {
//
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @Autowired
//    private ShoppingCartRepository shoppingCartRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private User user;
//    private Product product1;
//    private Product product2;
//    private ShoppingCart shoppingCart;
//
//    @BeforeEach
//    void setUp() {
//        // ایجاد و ذخیره یک کاربر تست
//        user = new User();
//        user.setUsername("user123");
//        user.setEmail("user123@example.com");
//        user.setPassword("password123");
//        userRepository.save(user);
//
//        // ایجاد و ذخیره محصول اول
//        product1 = new Product();
//        product1.setName("Product 1");
//        product1.setPrice(50.0);
//        productRepository.save(product1);
//
//        // ایجاد و ذخیره محصول دوم
//        product2 = new Product();
//        product2.setName("Product 2");
//        product2.setPrice(50.0);
//        productRepository.save(product2);
//
//        // ایجاد سبد خرید و مقداردهی تمام فیلدها
//        shoppingCart = new ShoppingCart();
//        shoppingCart.setUser(user);
//        shoppingCart.setProducts(List.of(product1, product2));
//        shoppingCart.setTotalPrice(100.0);
//    }
//
//    @Test
//    @Transactional
//    void saveShoppingCart_ShouldPersistShoppingCartInDatabase() {
//        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);
//        assertNotNull(savedCart);
//        assertEquals("user123", savedCart.getUser().getUsername());
//        assertEquals(100.0, savedCart.getTotalPrice());
//        assertEquals(2, savedCart.getProducts().size());
//
//        printJson("Shopping cart saved successfully", savedCart);
//    }
//
//    @Test
//    void updateShoppingCart_ShouldUpdateShoppingCartInDatabase() {
//        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);
//        assertNotNull(savedCart);
//
//        ShoppingCart updatedCart = new ShoppingCart();
//        updatedCart.setUser(user);
//        updatedCart.setProducts(List.of(product1));
//        updatedCart.setTotalPrice(50.0);
//
//        shoppingCartService.updateShoppingCart(savedCart.getId(), updatedCart);
//
//        ShoppingCart foundCart = shoppingCartRepository.findById(savedCart.getId()).orElse(null);
//        assertNotNull(foundCart);
//        assertEquals(50.0, foundCart.getTotalPrice());
//        assertEquals(1, foundCart.getProducts().size());
//
//        printJson("Shopping cart updated in database", foundCart);
//    }
//
//    @Test
//    @Transactional
//    void deleteShoppingCart_ShouldRemoveShoppingCartFromDatabase() {
//        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);
//        assertNotNull(savedCart);
//
//        shoppingCartService.deleteShoppingCart(savedCart.getId());
//
//        ShoppingCart foundCart = shoppingCartRepository.findById(savedCart.getId()).orElse(null);
//        assertNull(foundCart);
//    }
//
//    private void printJson(String message, Object object) {
//        try {
//            String json = objectMapper.writeValueAsString(object);
//            System.out.println(message + ": " + json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}
