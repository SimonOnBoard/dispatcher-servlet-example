package ru.itis.servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.servlets.dto.SignUpDto;
import ru.itis.servlets.dto.UserDto;
import ru.itis.servlets.models.State;
import ru.itis.servlets.models.User;
import ru.itis.servlets.repositories.UsersRepository;

import java.util.UUID;

public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDto loadUserFromParameters(SignUpDto userData) {
        User user = User.builder()
                .mail(userData.getEmail())
                .password(passwordEncoder.encode(userData.getPassword()))
                .nick(userData.getNick())
                .birthDay(userData.getBirthDay())
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .login(userData.getLogin())
                .build();

        usersRepository.save(user);

        return UserDto.from(user);
    }
}
