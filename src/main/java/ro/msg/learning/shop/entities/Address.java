package ro.msg.learning.shop.entities;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addressCountry;

    private String addressCity;

    private String addressCounty;

    private String addressStreet;

}
