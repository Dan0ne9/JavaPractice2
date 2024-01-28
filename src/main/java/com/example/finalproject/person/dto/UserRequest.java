package com.example.finalproject.person.dto;

import com.example.finalproject.person.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;

    public User mapToUser(){
        return new User(firstName,lastName,email);
    }

}
