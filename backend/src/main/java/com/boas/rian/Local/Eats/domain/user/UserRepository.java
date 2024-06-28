package com.boas.rian.Local.Eats.domain.user;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    User findByEmailAndUserType(String email, UserType userType);
}
