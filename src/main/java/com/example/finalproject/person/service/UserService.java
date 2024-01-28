package com.example.finalproject.person.service;

import com.example.finalproject.person.dto.UserRequest;
import com.example.finalproject.person.dto.UserResponse;
import com.example.finalproject.person.entity.User;
import com.example.finalproject.person.exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Collection;


public interface UserService {
    Collection<UserResponse> getAllUsers();
    void savePerson(User user);
    User getUserById(Long userId) throws UserNotFoundException;
    void deleteUserById(Long userId);

    Page<User> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);

}
