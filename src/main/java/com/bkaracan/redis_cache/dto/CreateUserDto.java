package com.bkaracan.redis_cache.dto;

import com.bkaracan.redis_cache.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String username;
    private String password;

    public User toEntity(CreateUserDto createUserDto) {
       return User.builder()
               .username(createUserDto.getUsername())
               .password(createUserDto.getPassword())
               .build();
    }
}
