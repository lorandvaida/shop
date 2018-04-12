package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.OrderDetail;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Timestamp orderTimestamp;
    private Address deliveryAddress;
    private List<OrderDetail> orderDetailList;

}
