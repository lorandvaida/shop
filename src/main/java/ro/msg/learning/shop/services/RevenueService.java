package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Revenue;
import ro.msg.learning.shop.repositories.RevenueRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RevenueService {

    private final OrderService orderService;
    private final LocationService locationService;
    private final RevenueRepository revenueRepository;

    @Autowired
    public RevenueService(OrderService orderService, LocationService locationService,
                          RevenueRepository revenueRepository) {
        this.orderService = orderService;
        this.locationService = locationService;
        this.revenueRepository = revenueRepository;
    }

    public void calculateRevenue() {

        List<Location> locationList = locationService.readLocations();

        for (Location location : locationList) {

            BigDecimal revenueSum = orderService.getRevenueSum(location);
            if (revenueSum == null) {
                revenueSum = new BigDecimal(0);
            }

            //Save revenue
            Revenue revenue = new Revenue();
            revenue.setLocationId(location.getId());
            Date todayDate = new Date();
            revenue.setRevenueDate(new Timestamp(todayDate.getTime()));
            revenue.setRevenueSum(revenueSum);

            revenueRepository.save(revenue);
        }
    }

}
