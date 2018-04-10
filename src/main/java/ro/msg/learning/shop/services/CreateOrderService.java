package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.strategy.LocationContext;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Service
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final SingleLocationStrategy singleLocationStrategy;
    private final NearestLocationStrategy nearestLocationStrategy;

    @Value("${strategyType}")
    private String strategyType;

    @Autowired
    public CreateOrderService(OrderRepository orderRepository, StockService stockService,
                              SingleLocationStrategy singleLocationStrategy, NearestLocationStrategy nearestLocationStrategy) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.singleLocationStrategy = singleLocationStrategy;
        this.nearestLocationStrategy = nearestLocationStrategy;
    }

    public Order createOrder(CreateOrderDto createOrderDto) throws NoLocationException {


        LocationContext locationContext = new LocationContext(loadStrategy(), createOrderDto);
        Location location = locationContext.getLocation();

        if (location != null) {

            //a store was found, make order

            Order order = new Order();
            order.setShippedFrom(location);
            order.setAddress(createOrderDto.getDeliveryAddress());
            order.setOrderDetails(createOrderDto.getOrderDetailList());


            orderRepository.save(order);

            stockService.subtractStock(createOrderDto.getOrderDetailList(), location);

            return order;
        } else {

            throw new NoLocationException(location.getId());
        }
    }

    public LocationStrategy loadStrategy() {

        switch (strategyType) {

            case "SingleLocationStrategy":
                return singleLocationStrategy;
            case "NearestLocationStrategy":
                return nearestLocationStrategy;
            default:
                return nearestLocationStrategy;
        }

    }

}
