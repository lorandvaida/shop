package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock readStock(int stockId) {

        return stockRepository.findOne(stockId);
    }

    public void  subtractStock(Product product, int quantity, Location location) {

        List<Stock> allStocks = readStocks();

        for(Stock stock : allStocks) {

            if(stock.getProduct().getId() == product.getId() && stock.getLocation().getId() == location.getId() ) {

                stock.setQuantity(stock.getQuantity() - quantity);
                stockRepository.save(stock);
            }
        }

    }

    public void subtractStock(List<OrderDetail> orderDetailList, Location location) {

        for(OrderDetail orderDetail : orderDetailList) {

            subtractStock(orderDetail.getProduct(), orderDetail.getQuantity(), location);
        }
    }

    public List<Stock> readStocks() {

        Iterable<Stock> iterableStocks = stockRepository.findAll();
        List<Stock> stockList = new ArrayList<>();

        if(iterableStocks != null) {
            for(Stock stock : iterableStocks) {
                stockList.add(stock);
            }
        }

        return stockList;
    }

    public Stock saveStock(Stock stock) {

        return stockRepository.save(stock);
    }

    public void deleteStock(int stockId) {

        stockRepository.delete(stockId);
    }

    public void deleteAllStocks() {

        stockRepository.deleteAll();
    }

}
