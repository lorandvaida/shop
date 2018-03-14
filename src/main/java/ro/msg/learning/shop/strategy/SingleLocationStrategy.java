package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.services.StockService;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleLocationStrategy implements  LocationStrategy {

    @Autowired
    private StockService stockService;

    @Override
    public Location getLocation(CreateOrderDto createOrderDto) {

        List<Stock> allStocks = stockService.readStocks();
        int requiredProductId = createOrderDto.getOrderDetail().getProduct().getId();
        int requiredProductQuantity = createOrderDto.getOrderDetail().getQuantity();

        for(Stock stock : allStocks) {

            if(stock.getProduct().getId() == requiredProductId && stock.getQuantity() >= requiredProductQuantity) {

                return stock.getLocation();
            }
        }

        return null;
    }
}
