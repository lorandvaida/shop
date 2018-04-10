package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order readOrder(int orderId) {

        return orderRepository.findOne(orderId);
    }

    public List<Order> readOrders() {

        Iterable<Order> iterableOrders = orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();

        if(iterableOrders != null) {
            for(Order order : iterableOrders) {
                orderList.add(order);
            }
        }

        return orderList;
    }

    public Order saveOrder(Order order) {

        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {

        orderRepository.delete(orderId);
    }

    public void deleteAllOrders() {

        orderRepository.deleteAll();
    }
}
