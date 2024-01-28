package com.example.finalproject.person.mapper;


import com.example.finalproject.person.dto.UserResponse;
import com.example.finalproject.person.entity.User;

public class UserMapper {
    public static UserResponse mapToUserDto(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static User mapToUser(UserResponse userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }

}
