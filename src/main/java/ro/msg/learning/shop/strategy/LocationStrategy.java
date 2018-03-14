package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;

@Component
public interface LocationStrategy {

    public Location getLocation(CreateOrderDto createOrderDto);
}
