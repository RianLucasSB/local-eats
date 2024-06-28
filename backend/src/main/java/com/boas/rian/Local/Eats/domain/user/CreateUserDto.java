package com.boas.rian.Local.Eats.domain.user;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CreateUserDto(
        String name,
        String email,
        String password
) {
}
