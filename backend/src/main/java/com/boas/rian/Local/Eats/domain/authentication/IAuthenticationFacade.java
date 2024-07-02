package com.boas.rian.Local.Eats.domain.authentication;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import com.boas.rian.Local.Eats.domain.user.User;

public interface IAuthenticationFacade {
    User retrieveUser(UserType userType);
}
