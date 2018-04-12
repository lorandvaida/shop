package ro.msg.learning.shop.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RevenueCalculator {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        System.out.println("The time is: " + dateFormat.format(new Date()));
    }
}
