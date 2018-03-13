package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private StockService stockService;
    @Autowired
    private LocationRepository locationRepository;

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

    public Location saveLocation(Location location) {

        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId) {

        locationRepository.delete(locationId);
    }

    public void deleteAllLocations() {

        locationRepository.deleteAll();
    }

    public List<Stock> getStockList(int locationId) {

        List<Stock> allSotckList = stockService.readStocks();
        List<Stock> resultStockList = new ArrayList<>();

        for(Stock stock : allSotckList) {

            if(locationId == stock.getLocation().getId()) {

                resultStockList.add(stock);
            }
        }

        return resultStockList;
    }

}
