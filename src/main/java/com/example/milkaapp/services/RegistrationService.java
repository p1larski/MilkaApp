package com.example.milkaapp.services;

import com.example.milkaapp.models.ConfirmationToken;
import com.example.milkaapp.models.User;
import com.example.milkaapp.models.UserRole;
import com.example.milkaapp.models.modelsDto.UserDto;
import com.example.milkaapp.repositories.UserRepository;
import com.example.milkaapp.services.UserService;
import com.example.milkaapp.tools.mailSender.MailSende;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;
    private MailSende mailSende;

    public String register(UserDto userDto) {

        String token = userService.signUpUser(
                new User(userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail(),
                        userDto.getPassword(),
                        userDto.getPhoneNumber(),
                        UserRole.USER));

        String link = "http://localhost:8080/registration/confirm?token=" + token;

        mailSende.sendSimpleMessage(userDto.getEmail(), link);

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.confirmUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
