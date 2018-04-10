package ro.msg.learning.shop.controllers;

import com.google.maps.model.DistanceMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.services.CreateOrderService;
import ro.msg.learning.shop.services.OrderService;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;

import java.net.InetSocketAddress;
import java.net.Proxy;
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
