package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Transactional
    public Optional<Payment> updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(existingPayment -> {
            existingPayment.setOrder(updatedPayment.getOrder());
            existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
            existingPayment.setStatus(updatedPayment.getStatus());
            return paymentRepository.save(existingPayment);
        });
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
