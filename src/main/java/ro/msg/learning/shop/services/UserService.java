package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {

        userRepository.save(encodePassword(user));
        //userRepository.save(user);
    }

    public List<User> readUsers() {

        Iterable<User> iterableUsers = userRepository.findAll();
        List<User> userList = new ArrayList<>();

        if(iterableUsers != null) {
            for(User user : iterableUsers) {
                userList.add(user);
            }
        }

        return userList;
    }

    public User findUserByUsername(String username) {

        List<User> userList = readUsers();

        for(User user : userList) {

            if(username.equalsIgnoreCase(user.getUsername())) {

                return user;
            }
        }

        return null;
    }

    public User findOneUser(String username, String password) {

        List<User> userList = readUsers();

        for(User user : userList) {
            if(username.equals(user.getUsername()) && passwordEncoder.matches(password,user.getPassword())) {

                return user;
            }
        }

        return null;
    }

    public User encodePassword(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return user;
    }

}
