package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail readOrderDetail(int orderDetailId) {

        return orderDetailRepository.findOne(orderDetailId);
    }

    public List<OrderDetail> readOrderDetails() {

        Iterable<OrderDetail> iterableOrderDetails = orderDetailRepository.findAll();
        List<OrderDetail> orderDetailList = new ArrayList<>();

        if(iterableOrderDetails != null) {
            for(OrderDetail orderDetail : iterableOrderDetails) {
                orderDetailList.add(orderDetail);
            }
        }

        return orderDetailList;
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {

        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(int orderDetailId) {

        orderDetailRepository.delete(orderDetailId);
    }

    public void deleteAllOrderDetails() {

        orderDetailRepository.deleteAll();
    }
}
