package ro.msg.learning.shop.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class LocationMapper {

    private String[] destination_addresses;
    private String[] origin_addresses;
    private Row[] rows;
}
