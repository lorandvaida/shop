package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final StockService stockService;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(StockService stockService, LocationRepository locationRepository) {
        this.stockService = stockService;
        this.locationRepository = locationRepository;
    }

    public Location readLocation(int locationId) {

        return locationRepository.findOne(locationId);
    }

    public List<Location> readLocations() {

        Iterable<Location> iterableLocations = locationRepository.findAll();
        List<Location> locationList = new ArrayList<>();

        if(iterableLocations != null) {
            for(Location location : iterableLocations) {
                locationList.add(location);
            }
        }

        return locationList;
    }

    public Location getLocationByAddress(Address address) {

        List<Location> locationList = readLocations();

        for(Location location : locationList) {

            if(address.getAddressStreet().equalsIgnoreCase(location.getAddress().getAddressStreet())
            && address.getAddressCity().equalsIgnoreCase(location.getAddress().getAddressCity())
            && address.getAddressCountry().equalsIgnoreCase(location.getAddress().getAddressCountry())) {

                return location;
            }
        }

        return null;
    }

    public List<Location> getAvailableLocationsForOrder(CreateOrderDto createOrderDto) {

        List<Stock> allStocks = stockService.readStocks();
        List<OrderDetail> requiredProducts = createOrderDto.getOrderDetailList();
        List<Location> availableLocationsList = new ArrayList<>();

        for(Stock stock : allStocks) {

            availableLocationsList.add(stock.getLocation());
        }

        // location list for product 1, where can we find them

        List<Location> firstProductLocations = new ArrayList<>();

        OrderDetail firstProduct = requiredProducts.get(0);

        for(Stock stock : allStocks) {

            if(stock.getProduct().getId() == firstProduct.getProduct().getId() && stock.getQuantity() >= firstProduct.getQuantity()) {

                firstProductLocations.add(stock.getLocation());
            }
            else {

                availableLocationsList.remove(stock.getLocation());
            }
        }


        for(Location location : firstProductLocations) {

            // at this locations we can find the first product, we have to check to other products

            for(OrderDetail orderDetail : requiredProducts) {

                //we have a required product, check if it's aviable at the location, if not, remove location

                if(!productAviableAtLocation(orderDetail.getProduct(), orderDetail.getQuantity(), location)) {

                    availableLocationsList.remove(location);
                }
            }
        }

        return availableLocationsList;
    }


    private boolean productAviableAtLocation(Product product, int quantity, Location location) {


        List<Stock> stockListAtLocation = stockService.getStockList(location.getId());

        for(Stock stock : stockListAtLocation) {

            if(stock.getProduct().getId() == product.getId() && stock.getQuantity() >= quantity) {

                return true;
            }
        }

        return false;
    }

    public Location saveLocation(Location location) {

        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId) {

        locationRepository.delete(locationId);
    }

    public void deleteAllLocations() {

        locationRepository.deleteAll();
    }

}
