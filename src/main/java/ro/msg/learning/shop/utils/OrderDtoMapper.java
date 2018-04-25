package ro.msg.learning.shop.utils;

import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;

import java.sql.Timestamp;
import java.util.*;

public class OrderDtoMapper {

    public static OrderDto mapOrderDto(Map<String, Object> data) {

        OrderDto orderDto = new OrderDto();

        @SuppressWarnings("unchecked")
        Map<String, Object> deliveryAddressMap = (Map<String, Object>) data.get("deliveryAddress");

        GregorianCalendar gregorianCalendar = (GregorianCalendar) data.get("orderTimestamp");
        Date myDate = gregorianCalendar.getTime();
        orderDto.setOrderTimestamp(new Timestamp(myDate.getTime()));

        Address deliveryAddress = new Address();
        deliveryAddress.setAddressCountry((String) deliveryAddressMap.get("addressCountry"));
        deliveryAddress.setAddressCounty((String) deliveryAddressMap.get("addressCounty"));
        deliveryAddress.setAddressCity((String) deliveryAddressMap.get("addressCity"));
        deliveryAddress.setAddressStreet((String) deliveryAddressMap.get("addressStreet"));
        orderDto.setDeliveryAddress(deliveryAddress);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        ODataDeltaFeedImpl products = (ODataDeltaFeedImpl) data.get("orderDetailList");
        products.getEntries().forEach(p -> {

            Integer productId = (Integer) p.getProperties().get("productId");
            Integer quantity = (Integer) p.getProperties().get("quantity");

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(quantity);
            Product product = new Product();
            product.setId(productId);
            orderDetail.setProduct(product);

            orderDetailList.add(orderDetail);

        });

        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}
