package com.bkaracan.redis_cache.service;

import com.bkaracan.redis_cache.dto.CreateUserDto;
import com.bkaracan.redis_cache.dto.UpdateUserDto;
import com.bkaracan.redis_cache.entity.User;

import java.util.List;

public interface UserService {


    User createUser(CreateUserDto createUserDto);

    List<User> getAllUsers();

    User getUserById(long id);

    User updateUser(UpdateUserDto updateUserDto);

    String deleteById(long id);
}
