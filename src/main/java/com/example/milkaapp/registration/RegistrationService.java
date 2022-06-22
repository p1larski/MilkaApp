package com.example.milkaapp.registration;

import com.example.milkaapp.models.User;
import com.example.milkaapp.models.UserRole;
import com.example.milkaapp.models.modelsDto.UserDto;
import com.example.milkaapp.repositories.UserRepository;
import com.example.milkaapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private UserService userService;

    public String register(UserDto userDto) {

        return userService.signUpUser(
                new User(userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail(),
                        userDto.getPassword(),
                        userDto.getPhoneNumber(),
                        UserRole.USER));
    }
}
