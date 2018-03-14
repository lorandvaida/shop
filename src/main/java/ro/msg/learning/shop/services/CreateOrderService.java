package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.strategy.LocationContext;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private SingleLocationStrategy singleLocationStrategy;

    public Order createOrder(CreateOrderDto createOrderDto) throws NoLocationException {

        LocationContext locationContext= new LocationContext(singleLocationStrategy, createOrderDto);
        Location location = locationContext.getLocation();

        if(location != null) {

            //a store was found, make order

            Order order = new Order();
            order.setShippedFrom(location);
            order.setAddress(createOrderDto.getDeliveryAddress());
            order.setOrderDetails(createOrderDto.getOrderDetailList());

            orderRepository.save(order);

            stockService.subtractStock(createOrderDto.getOrderDetailList(), location);

            return order;
        }
        else {

            throw new NoLocationException("Location not found.");
        }
    }
}
