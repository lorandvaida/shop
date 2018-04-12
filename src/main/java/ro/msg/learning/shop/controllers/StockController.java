package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.utils.StockDtoMapper;

import java.util.List;

@RestController
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/stockList/{id}", method = RequestMethod.GET)
    public List<StockDto> readStock(@PathVariable("id") int locationId) {

        return StockDtoMapper.stockListToStockDtoList(stockService.getStockList(locationId));
    }

    @RequestMapping(value = "/stockList", method = RequestMethod.GET)
    public List<StockDto> readStockList() {

        return StockDtoMapper.stockListToStockDtoList(stockService.readStocks());
    }
}
