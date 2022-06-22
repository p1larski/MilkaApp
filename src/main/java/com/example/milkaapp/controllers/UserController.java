/*package com.example.milkaapp.controllers;

import com.example.milkaapp.models.User;
import com.example.milkaapp.models.modelsDto.UserDto;
import com.example.milkaapp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/client")
    public User newClient (@RequestBody UserDto userDto){
        return userService.addClient(userDto);
    }

}*/
