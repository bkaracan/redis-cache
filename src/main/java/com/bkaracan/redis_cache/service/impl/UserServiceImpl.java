package com.bkaracan.redis_cache.service.impl;

import com.bkaracan.redis_cache.dto.CreateUserDto;
import com.bkaracan.redis_cache.dto.UpdateUserDto;
import com.bkaracan.redis_cache.entity.User;
import com.bkaracan.redis_cache.repository.UserRepository;
import com.bkaracan.redis_cache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @CacheEvict(value = {"users","user_id"}, allEntries = true)
    public User createUser(CreateUserDto createUserDto) {

        var user = userRepository.save(createUserDto.toEntity(createUserDto));
        return user;
    }

    @Override
    @Cacheable(value = "users", key = "#root.methodName", unless = "#result == null")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "user_id", key = "#root.methodName + #id")
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @CachePut(cacheNames = "user_id", key = "'getUserById' + #updateUserDto.id", unless = "#result == null")
    public User updateUser(UpdateUserDto updateUserDto) {
        Optional<User> optionalUser = userRepository.findById(updateUserDto.getId());
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.setPassword(updateUserDto.getPassword());
            return userRepository.save(user);
        } else  {
            return null;
        }
    }

    @Override
    @CacheEvict(value = {"users", "user_id"}, allEntries = true)
    public String deleteById(long id) {
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted";
    }
}
