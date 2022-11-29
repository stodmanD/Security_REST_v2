package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRole();
    void saveRole (Role role);

    Role getRole(int id);

    void deleteRoleById(int id);

    Role getByName(String roleName) throws Exception;

    Set<Role> getRoleSet(String[] role) throws Exception;
}
