package com.example.milkaapp.models.modelsDto;

import com.example.milkaapp.models.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNumber;
}
