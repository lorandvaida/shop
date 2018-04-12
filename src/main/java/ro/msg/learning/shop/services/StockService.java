package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {

        this.stockRepository = stockRepository;
    }

    private void subtractStock(Product product, int quantity, Location location) {

        stockRepository.updateStock(product.getId(), quantity, location.getId());
    }

    public List<Stock> getStockList(int locationId) {

        List<Stock> allStockList = readStocks();
        List<Stock> resultStockList = new ArrayList<>();

        for (Stock stock : allStockList) {

            if (locationId == stock.getLocation().getId()) {

                resultStockList.add(stock);
            }
        }

        if (resultStockList.isEmpty()) {

            throw new NoLocationException();
        } else {

            return resultStockList;
        }
    }

    public void subtractStock(List<OrderDetail> orderDetailList, Location location) {

        for (OrderDetail orderDetail : orderDetailList) {

            subtractStock(orderDetail.getProduct(), orderDetail.getQuantity(), location);
        }
    }

    public List<Stock> readStocks() {

        return stockRepository.findAll();
    }

}
