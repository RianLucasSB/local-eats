package com.boas.rian.Local.Eats.domain.user;

import com.boas.rian.Local.Eats.domain.enums.UserType;

public record CreateUserResponseDto(
        Long id,
        String name,
        String email,
        UserType userType,
        String token
) {
    public CreateUserResponseDto(User user, String token){
        this(user.getId(), user.getName(), user.getEmail(), user.getUserType(), token);
    }
}
