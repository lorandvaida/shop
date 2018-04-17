package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.config.StrategyConfig;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.repositories.OrderDetailRepository;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.util.Date;

@Service
@Transactional
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final SingleLocationStrategy singleLocationStrategy;
    private final NearestLocationStrategy nearestLocationStrategy;
    private final StrategyConfig strategyConfig;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public CreateOrderService(OrderRepository orderRepository, StockService stockService,
                              SingleLocationStrategy singleLocationStrategy,
                              NearestLocationStrategy nearestLocationStrategy,
                              StrategyConfig strategyConfig,
                              OrderDetailRepository orderDetailRepository) {

        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.singleLocationStrategy = singleLocationStrategy;
        this.nearestLocationStrategy = nearestLocationStrategy;
        this.strategyConfig = strategyConfig;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Order createOrder(OrderDto createOrderDto) throws NoLocationException {

        LocationStrategy locationStrategy = strategyConfig.loadStrategy(singleLocationStrategy,
                nearestLocationStrategy);

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
