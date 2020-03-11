package ru.itis.servlets.services;

import freemarker.core.OptInTemplateClassResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.servlets.models.MyUserDetails;
import ru.itis.servlets.models.State;
import ru.itis.servlets.models.User;
import ru.itis.servlets.repositories.UsersRepository;

import java.util.Optional;

public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    /*Here we are using dummy data, you need to load user data from
     database or other third party application*/
        User user = findUserbyUername(username);

        if (user != null) {
            return MyUserDetails.builder().user(user).build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    private User findUserbyUername(String username) {
        Optional<User> user = usersRepository.findByLogin(username);
        return user.orElse(null);
    }
}