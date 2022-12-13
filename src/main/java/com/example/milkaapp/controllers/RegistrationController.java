package com.example.milkaapp.controllers;

import com.example.milkaapp.models.modelsDto.UserDto;
import com.example.milkaapp.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody UserDto userDto){
        return registrationService.register(userDto);
    }

    @GetMapping(value = "/confirm")
    @ResponseBody
    public String confirmEmail(@RequestParam String token){
            registrationService.confirmToken(token);
        return "cinf";
    }
}
