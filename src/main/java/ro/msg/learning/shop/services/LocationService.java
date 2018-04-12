package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationService {

    private final StockService stockService;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(StockService stockService, LocationRepository locationRepository) {
        this.stockService = stockService;
        this.locationRepository = locationRepository;
    }

    private List<Location> readLocations() {

        return locationRepository.findAll();
    }

    public Location getLocationByAddress(Address address) {

        List<Location> locationList = readLocations();

        for (Location location : locationList) {

            if (address.getAddressStreet().equalsIgnoreCase(location.getAddress().getAddressStreet())
                    && address.getAddressCity().equalsIgnoreCase(location.getAddress().getAddressCity())
                    && address.getAddressCountry().equalsIgnoreCase(location.getAddress().getAddressCountry())) {

                return location;
            }
        }

        return null;
    }

    public List<Location> getAvailableLocationsForOrder(OrderDto createOrderDto) {

        List<Stock> allStocks = stockService.readStocks();
        List<OrderDetail> requiredOrders = createOrderDto.getOrderDetailList();

        // location list for product 1, where can we find them
        List<Location> firstProductLocationsList = new ArrayList<>();

        OrderDetail firstProduct = requiredOrders.get(0);

        for (Stock stock : allStocks) {

            if (stock.getProduct().getId() == firstProduct.getProduct().getId()
                    && stock.getQuantity() >= firstProduct.getQuantity()) {

                firstProductLocationsList.add(stock.getLocation());
            }
        }

        List<Location> resultList = new ArrayList<>(firstProductLocationsList);

        for (Location currentLocation : firstProductLocationsList) {

            // at this locations we can find the first product, we have to check to other products
            for (OrderDetail orderDetail : requiredOrders) {

                //we have a required product, check if it's available at the location, if not, remove location
                if (!productAvailableAtLocation(orderDetail.getProduct(), orderDetail.getQuantity(), currentLocation)) {

                    resultList.remove(currentLocation);
                    break;
                }
            }
        }

        return resultList;
    }


    private boolean productAvailableAtLocation(Product product, int quantity, Location location) {

        List<Stock> stockListAtLocation = stockService.getStockList(location.getId());

        for (Stock stock : stockListAtLocation) {

            if (stock.getProduct().getId() == product.getId() && stock.getQuantity() >= quantity) {

                return true;
            }
        }

        return false;
    }

}
