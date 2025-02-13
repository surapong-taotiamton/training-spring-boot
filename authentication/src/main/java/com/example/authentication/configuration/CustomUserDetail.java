package com.example.authentication.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Slf4j
public class CustomUserDetail implements UserDetailsService {

    private HashMap<String, User> allUserInDb = new HashMap();

    public CustomUserDetail() {
        User user1 = new User("belle", "belle_password", new ArrayList<>());
        User user2 = new User("pat", "pat_password", new ArrayList<>());
        allUserInDb.put(user1.getUsername(), user1);
        allUserInDb.put(user2.getUsername(), user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("In loadUserByUsername : {}", username);

        User userInDb = allUserInDb.get(username);

        if (userInDb == null) {
            throw new UsernameNotFoundException("Not found user : " + username);
        }
        return userInDb;
    }
}
