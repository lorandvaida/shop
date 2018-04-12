package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.services.LocationService;

import java.util.List;

@Component
public class SingleLocationStrategy implements LocationStrategy {

    private final LocationService locationService;

    @Autowired
    public SingleLocationStrategy(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Location getLocation(OrderDto createOrderDto) {

        List<Location> availableLocationsList = locationService.getAvailableLocationsForOrder(createOrderDto);

        if (availableLocationsList.isEmpty()) {

            return null;
        } else {

            return availableLocationsList.get(0);
        }
    }
}
