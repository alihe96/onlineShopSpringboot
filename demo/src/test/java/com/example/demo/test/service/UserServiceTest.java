package com.example.demo.test.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // ایجاد چند کاربر تست
        user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");
        user1.setRole("USER");
        user1.setShippingAddress("Shipping Address 1");
        user1.setBillingAddress("Billing Address 1");

        user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");
        user2.setRole("USER");
        user2.setShippingAddress("Shipping Address 2");
        user2.setBillingAddress("Billing Address 2");

        user3 = new User();
        user3.setUsername("user3");
        user3.setEmail("user3@example.com");
        user3.setPassword("password3");
        user3.setRole("USER");
        user3.setShippingAddress("Shipping Address 3");
        user3.setBillingAddress("Billing Address 3");

        // پرینت اطلاعات کاربران
        printJson("Set up users for test", new User[]{user1, user2, user3});
    }

    @Test
    @Transactional
    void saveUsers_ShouldPersistUsersInDatabase() {
        // ذخیره کاربران در دیتابیس
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

        // بررسی ذخیره داده‌ها در دیتابیس
        User foundUser1 = userRepository.findById(user1.getId()).orElse(null);
        User foundUser2 = userRepository.findById(user2.getId()).orElse(null);
        User foundUser3 = userRepository.findById(user3.getId()).orElse(null);

        assertNotNull(foundUser1);
        assertEquals("user1", foundUser1.getUsername());

        assertNotNull(foundUser2);
        assertEquals("user2", foundUser2.getUsername());

        assertNotNull(foundUser3);
        assertEquals("user3", foundUser3.getUsername());

        // پرینت اطلاعات کاربران ذخیره شده
        printJson("Users saved successfully", new User[]{foundUser1, foundUser2, foundUser3});
    }

    @Test
    void deleteUser_ShouldRemoveOneUserButOthersRemain() {
        // ذخیره کاربران
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

        // پرینت اطلاعات کاربران ذخیره شده
        printJson("Users saved for deletion", new User[]{user1, user2, user3});

        // حذف یک کاربر (مثلاً user2)
        userService.deleteUserById(user2.getId());

        // بررسی اینکه کاربر حذف شده نباشد
        User foundUser1 = userRepository.findById(user1.getId()).orElse(null);
        User foundUser3 = userRepository.findById(user3.getId()).orElse(null);
        User foundUser2 = userRepository.findById(user2.getId()).orElse(null); // باید null باشد

        assertNotNull(foundUser1);
        assertNotNull(foundUser3);
        assertNull(foundUser2);

        // پرینت اطلاعات کاربران بعد از حذف
        printJson("Remaining users after deletion", new User[]{foundUser1, foundUser3});
    }

    // متد کمکی برای پرینت اطلاعات به صورت JSON
    private void printJson(String message, Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            System.out.println(message + ": " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
