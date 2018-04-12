package ro.msg.learning.shop.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Element {

    private Distance distance;
    private Duration duration;
}
