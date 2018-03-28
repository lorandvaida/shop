package ro.msg.learning.shop.services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.entities.UserPrivilege;
import ro.msg.learning.shop.entities.UserRole;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);
        UserBuilder userBuilder = null;
        userService.readUsers();

        if(user != null) {

            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            List<UserRole> userRolesList = user.getRoles();
            List<String> userRolesStringList = new ArrayList<>();

            if(userRolesList != null) {
                for(UserRole role : userRolesList) {

                    userRolesStringList.add(role.getRole());
                }
            }
            userBuilder.roles(userRolesStringList.toArray(new String[0]));
        }
        else {

            throw new UsernameNotFoundException("User not found.");
        }

        return userBuilder.build();
    }

}
