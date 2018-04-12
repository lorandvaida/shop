package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.services.CreateOrderService;
import ro.msg.learning.shop.services.OrderService;

import java.util.List;

@RestController
public class CreateOrderController {

    private final CreateOrderService createOrderService;
    private final OrderService orderService;

    @Autowired
    public CreateOrderController(CreateOrderService createOrderService, OrderService orderService) {
        this.createOrderService = createOrderService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Order createOrder(@RequestBody OrderDto createOrderDto) {

        try {

            return createOrderService.createOrder(createOrderDto);

        } catch (Exception e) {

            System.out.print("Error: " + e);
            return new Order();
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders() {

        return orderService.readOrders();
    }

}
