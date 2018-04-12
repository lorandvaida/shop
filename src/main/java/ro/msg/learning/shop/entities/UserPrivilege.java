package ro.msg.learning.shop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "privileges")
    private List<UserRole> userRoles;
}
