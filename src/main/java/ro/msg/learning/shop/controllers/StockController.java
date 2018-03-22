package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.NoLocationException;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.utils.StockDtoMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stockList/{id}", method = RequestMethod.GET)
    public List<StockDto> createOrder(@PathVariable("id") int locationId) throws Exception {

        return StockDtoMapper.stockListToStockDtoList(stockService.getStockList(locationId));
    }
}
