package com.stolypin.securitybootstrap.repository;

import com.stolypin.securitybootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole (String username);
}
