package ro.msg.learning.shop.utils;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    public static OrderDto mapOrderDto(Map<String, Object> data) {

        OrderDto orderDto = new OrderDto();

        GregorianCalendar gregorianCalendar = (GregorianCalendar) data.get("orderTimestamp");
        Date myDate = gregorianCalendar.getTime();
        orderDto.setOrderTimestamp(new Timestamp(myDate.getTime()));

        @SuppressWarnings("unchecked")
        Map<String, Object> deliveryAddressMap = (Map<String, Object>) data.get("deliveryAddress");
        orderDto.setDeliveryAddress(getOrderAddressFromData(deliveryAddressMap));

        ODataDeltaFeedImpl products = (ODataDeltaFeedImpl) data.get("orderDetailList");
        List<ODataEntry> oDataEntryList = products.getEntries();
        orderDto.setOrderDetailList(oDataEntryToOrderDetailList(oDataEntryList));

        return orderDto;
    }

    private static List<OrderDetail> oDataEntryToOrderDetailList(List<ODataEntry> oDataEntryList) {

        Function<ODataEntry, OrderDetail> oDataEntryToOrderDetail
                = oDataEntry -> {

            @SuppressWarnings("unchecked")
            Map<String, Object> productMap = (Map<String, Object>) oDataEntry.getProperties().get("product");

            Product product = new Product();
            product.setId((Integer) productMap.get("id"));
            product.setName((String) productMap.get("name"));
            product.setName((String) productMap.get("description"));
            product.setPrice((Double) productMap.get("price"));
            product.setWeight((Double) productMap.get("weight"));

            ProductCategory productCategory = new ProductCategory();
            productCategory.setName((String) productMap.get("categoryName"));
            product.setCategory(productCategory);

            Supplier supplier = new Supplier();
            supplier.setName((String) productMap.get("supplierName"));
            product.setSupplier(supplier);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity((Integer) oDataEntry.getProperties().get("quantity"));
            orderDetail.setProduct(product);

            return orderDetail;
        };

        return oDataEntryList.stream().map(oDataEntryToOrderDetail).collect(Collectors.toList());
    }


    private static Address getOrderAddressFromData(Map<String, Object> deliveryAddressMap) {

        Address deliveryAddress = new Address();
        deliveryAddress.setAddressCountry((String) deliveryAddressMap.get("addressCountry"));
        deliveryAddress.setAddressCounty((String) deliveryAddressMap.get("addressCounty"));
        deliveryAddress.setAddressCity((String) deliveryAddressMap.get("addressCity"));
        deliveryAddress.setAddressStreet((String) deliveryAddressMap.get("addressStreet"));

        return deliveryAddress;
    }
}
