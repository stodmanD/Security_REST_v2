package com.stolypin.securityrest.services;


import com.stolypin.securityrest.model.Role;
import com.stolypin.securityrest.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDataLoader {

    private final RoleService roleService;
    private final UserService userService;

    public UserDataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void loadDataUser() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("ROLE_ADMIN"));
        allRoles.add(new Role("ROLE_USER"));
        roleService.addRole(allRoles);

        User user = new User("Dmitry", "Stolypin", "IT",1000, "123","dmitryS");
        user.setRoles("ROLE_ADMIN");
        userService.addUser(user);

        User user2 = new User("Oleg", "Ivanov", "Sale",1000, "345","olegI");
        user2.setRoles("ROLE_USER");
        userService.addUser(user2);

        User user3 = new User("Olga", "Fomina", "HR",700, "345","olgaF");
        user3.setRoles("ROLE_USER");
        userService.addUser(user3);

        User user4 = new User("Igor", "Petrov", "Marketing",600, "345","igorP");
        user4.setRoles("ROLE_USER");
        userService.addUser(user4);

        User user5 = new User("Masha", "Ermolina", "OZHO",400, "345","maryE");
        user5.setRoles("ROLE_ADMIN");
        userService.addUser(user5);
    }
}
