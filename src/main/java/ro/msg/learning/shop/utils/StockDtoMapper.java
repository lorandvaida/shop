package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StockDtoMapper {

    public static List<StockDto> stockListToStockDtoList(List<Stock> stockList) {

        Function<Stock, StockDto> stockToStockDto
                = stock -> {

            StockDto stockDto = new StockDto();
            stockDto.setProductName(stock.getProduct().getName());
            stockDto.setLocationName(stock.getLocation().getName());
            stockDto.setQuantity(stock.getQuantity());

            return stockDto;
        };

        return stockList.stream().map(stockToStockDto).collect(Collectors.toList());

    }

}
