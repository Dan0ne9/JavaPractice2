package com.example.finalproject.person.service;


import com.example.finalproject.person.dto.UserRequest;
import com.example.finalproject.person.dto.UserResponse;
import com.example.finalproject.person.entity.User;
import com.example.finalproject.person.exceptions.UserNotFoundException;
import com.example.finalproject.person.mapper.UserMapper;
import com.example.finalproject.person.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public Collection<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }
    @Override
    public void savePerson(User user) {
        this.userRepository.save(user);
    }
    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(()->new UserNotFoundException("Пользователь не найден"));
        return user;
    }

    @Override
    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }


}
