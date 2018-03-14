package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.OrderDetail;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {

    private Timestamp orderTimestamp;
    private Address deliveryAddress;
    private OrderDetail orderDetail;

}
