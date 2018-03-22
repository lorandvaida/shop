package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entities.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockDtoMapper {

    public static StockDto stockToStockDto(Stock stock) {

        StockDto stockDto = new StockDto();
        stockDto.setProductName(stock.getProduct().getName());
        stockDto.setLocationName(stock.getLocation().getName());
        stockDto.setQuantity(stock.getQuantity());

        return stockDto;
    }

    public static List<StockDto> stockListToStockDtoList(List<Stock> stockList) {

        List<StockDto> stockDtoList = new ArrayList<>();

        for(Stock stock : stockList) {

            stockDtoList.add(stockToStockDto(stock));
        }

        return stockDtoList;
    }

}
