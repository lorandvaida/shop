package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Location;

@Component
public interface LocationStrategy {

    Location getLocation(OrderDto createOrderDto);
}
