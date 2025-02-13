package com.bkaracan.redis_cache.controller;

import com.bkaracan.redis_cache.dto.CreateUserDto;
import com.bkaracan.redis_cache.dto.UpdateUserDto;
import com.bkaracan.redis_cache.entity.User;
import com.bkaracan.redis_cache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto updateUserDto){
        return new ResponseEntity<>(userService.updateUser(updateUserDto), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUserById(@RequestParam long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
