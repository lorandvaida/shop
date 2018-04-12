package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.entities.UserRole;

import java.util.List;

@Service
@Transactional
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;

        if(user != null) {

            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            List<UserRole> userRolesList = user.getRoles();
            userBuilder.roles(userRolesList.stream().map(UserRole::getRole).toArray(String[]::new));
        }
        else {

            throw new UsernameNotFoundException("User not found.");
        }

        return userBuilder.build();
    }
}