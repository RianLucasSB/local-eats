package com.boas.rian.Local.Eats.domain.partner;

import com.boas.rian.Local.Eats.domain.enums.UserType;

public record CreatePartnerDto(
        String name,
        String email,
        String password,

        String cnpj

) {
}
