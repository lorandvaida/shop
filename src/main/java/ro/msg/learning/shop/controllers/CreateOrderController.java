package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.services.CreateOrderService;
import ro.msg.learning.shop.services.OrderService;

import java.util.List;

@RestController
public class CreateOrderController {

    @Autowired
    private CreateOrderService createOrderService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Order createOrder(@RequestBody CreateOrderDto createOrderDto) {

        Order order = new Order();

        try {

            order = createOrderService.createOrder(createOrderDto);

        }catch(Exception e) {
            System.out.print("Error: " + e);
        }

        return order;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders() {

        return orderService.readOrders();
    }

}
