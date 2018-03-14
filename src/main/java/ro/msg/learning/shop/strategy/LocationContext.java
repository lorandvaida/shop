package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationContext {

    private LocationStrategy locationStrategy;
    private CreateOrderDto createOrderDto;


    public Location getLocation(){

        return locationStrategy.getLocation(createOrderDto);
    }
}
