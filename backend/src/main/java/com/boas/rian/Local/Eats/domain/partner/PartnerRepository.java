package com.boas.rian.Local.Eats.domain.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("""
    select p from Partner p where p.user.id = :userId
    """)
    Partner findByUserId(Long userId);
}
