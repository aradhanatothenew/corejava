package com.demo.service;

import main.java.com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Test(expected = RuntimeException.class)
    public void testPlaceOrderWithOrder_shouldSetPriceWithTax() {
        Order order = new Order(2,"Item1",20.00);
        orderService.placeOrder(order);
        assertEquals(24.0,order.getPriceWithTax(),0.01);
    }

    @Test(expected = RuntimeException.class)
    public void testPlaceOrderWithOrder_shouldThrowDueToEmailFailure() {
        Order order = new Order(2,"Item1",20.00);
        orderService.placeOrder(order);
    }

    @Test
    public void testPlaceOrderWithCc_shouldSetPriceAndNotifyCustomer() {
        Order order = new Order(2,"Item1",20.00);
        assertTrue(orderService.placeOrder(order, "cc@gmail.com"));
        assertEquals(24.0, order.getPriceWithTax(), 0.01);
    }

    @Test
    public void testPlaceOrderWithOrder_shouldNotifyCustomer() {
        Order order = new Order(2,"Item1",20.00);
        orderService.placeOrder(order, "cc@gmail.com");
        assertTrue(order.isCustomerNotified());
    }

}
