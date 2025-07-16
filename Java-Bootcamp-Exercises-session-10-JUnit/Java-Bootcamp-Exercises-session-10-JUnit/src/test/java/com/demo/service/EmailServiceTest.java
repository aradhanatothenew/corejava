package com.demo.service;

import main.java.com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
    @InjectMocks
    EmailService emailService;

//    @Test(expected = RuntimeException.class)
//    public void shouldReturnSameObject() {
//        EmailService emailServiceObject = new EmailService();
//    }

    @Test(expected = RuntimeException.class)
    public void testSendEmailWithOrder_shouldThrowException(){
        Order order = new Order(2,"Item1",20.00);
        emailService.sendEmail(order);
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmailWithOrder_shouldNotNotifyCustomer(){
        Order order = new Order(2,"Item1",20.00);
        emailService.sendEmail(order);
        assertFalse(order.isCustomerNotified());
    }

    @Test
    public void testSendEmailWithCc_shouldNotifyCustomer() {
        Order order = new Order(2, "Item1", 20.00);
        assertTrue(order.isCustomerNotified());
    }

    @Test
    public void testSendEmailWithCc_shouldReturnTrue() {
        Order order = new Order(2, "Item1", 20.00);
        assertTrue(emailService.sendEmail(order, "cc@gmail.com"));
    }
}