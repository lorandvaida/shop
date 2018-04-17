package ro.msg.learning.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.services.RevenueService;

@Component
public class RevenueCalculator {

    private final RevenueService revenueService;

    @Autowired
    public RevenueCalculator(RevenueService revenueService) {
        this.revenueService = revenueService;
    }


    //cron = "[Seconds][Minutes][Hours][Day of month][Month][Day of week][Year]"
    @Scheduled(cron = "0 00 00 * * ?")
    public void reportCurrentTime() {

        revenueService.calculateRevenue();
    }

}
