package com.stolypin.securitybootstrap.services;

import com.stolypin.securitybootstrap.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRole();
    void saveRole (Role role);
    Role findByRole(String role);

    Role getRole(int id);

    void deleteRoleById(int id);

    Role getByName(String roleName) throws Exception;

    Set<Role> getRoleSet(String[] role) throws Exception;


}
