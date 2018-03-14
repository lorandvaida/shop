package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.services.StockService;

import java.util.List;

@Component
public class SingleLocationStrategy implements  LocationStrategy {

    @Autowired
    private StockService stockService;

    @Override
    public Location getLocation(CreateOrderDto createOrderDto) {

        List<Stock> allStocks = stockService.readStocks();
        List<OrderDetail> requiredProducts = createOrderDto.getOrderDetailList();

        for(Stock stock : allStocks) {

            boolean hasAllProductsFlag = true;

            for(OrderDetail orderDetail : requiredProducts) {

                if(stock.getProduct().getId() != orderDetail.getProduct().getId() || stock.getQuantity() < orderDetail.getQuantity()) {

                    hasAllProductsFlag = false;
                }
            }
            if(hasAllProductsFlag) {

                return stock.getLocation();
            }
        }

        return null;
    }
}
