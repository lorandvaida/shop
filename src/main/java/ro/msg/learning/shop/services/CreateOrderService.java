package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.repositories.OrderDetailRepository;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;

import java.util.Date;

@Service
@Transactional
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final OrderDetailRepository orderDetailRepository;
    private final LocationStrategy locationStrategy;

    @Autowired
    public CreateOrderService(OrderRepository orderRepository,
                              StockService stockService,
                              OrderDetailRepository orderDetailRepository,
                              LocationStrategy locationStrategy) {

        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.orderDetailRepository = orderDetailRepository;
        this.locationStrategy = locationStrategy;
    }

    public Order createOrder(OrderDto createOrderDto) throws NoLocationException {

        Location location = locationStrategy.getLocation(createOrderDto);

        if (location != null) {

            //a store was found, make order

            Order order = new Order();
            order.setShippedFrom(location);
            order.setAddress(createOrderDto.getDeliveryAddress());
            for (OrderDetail orderDetail : createOrderDto.getOrderDetailList()) {
                order.addOrderDetail(orderDetail);
            }
            //OrdeDto has timestamp, we will convert it to date for the Order entity
            Date timestampToDate = new Date(createOrderDto.getOrderTimestamp().getTime());
            order.setOrderDate(timestampToDate);


            //save the order
            orderRepository.save(order);
            orderDetailRepository.save(createOrderDto.getOrderDetailList());
            stockService.subtractStock(createOrderDto.getOrderDetailList(), location);

            return order;
        } else {

            throw new NoLocationException();
        }
    }

}
