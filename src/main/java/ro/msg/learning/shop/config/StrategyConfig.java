package ro.msg.learning.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class StrategyConfig {

    private final LocationService locationService;

    @Autowired
    public StrategyConfig(LocationService locationService) {
        this.locationService = locationService;
    }

    @Bean
    @Primary
    public LocationStrategy loadStrategy(@Value("${strategyType}") String strategyType) {

        switch (strategyType) {

            case "SingleLocationStrategy":
                return new SingleLocationStrategy(locationService);
            case "NearestLocationStrategy":
                return new NearestLocationStrategy(locationService);
            default:
                return new SingleLocationStrategy(locationService);
        }
    }

}
