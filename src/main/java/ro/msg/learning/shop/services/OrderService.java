package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.repositories.OrderRepository;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public List<Order> readOrders() {

        return orderRepository.findAll();
    }

}
