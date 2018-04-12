package ro.msg.learning.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class StrategyConfig {

    @Value("${strategyType}")
    private String strategyType;

    @Bean
    public LocationStrategy loadStrategy(SingleLocationStrategy singleLocationStrategy, NearestLocationStrategy nearestLocationStrategy) {

        switch (strategyType) {

            case "SingleLocationStrategy":
                return singleLocationStrategy;
            case "NearestLocationStrategy":
                return nearestLocationStrategy;
            default:
                return nearestLocationStrategy;
        }

    }
}
