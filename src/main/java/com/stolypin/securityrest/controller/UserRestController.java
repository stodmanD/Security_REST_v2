package com.stolypin.securityrest.controller;


import com.stolypin.securityrest.model.User;
import com.stolypin.securityrest.services.RoleService;
import com.stolypin.securityrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List <User> allUser = userService.getAllUsers();
        return allUser;
    }

    @GetMapping("/{id}")
    public User getUser (@PathVariable int id) {
        User user = userService.getUser(id);

        return user;
    }

@PostMapping
    public User addNewUser (@RequestBody User user) {
        userService.addUser(user);
    return user;
}
    @PutMapping
    public User editUser (@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchElementException("User by id" + id + "not found");
        }
        userService.deleteUser(id);
        return "User by " + id + "was deleted";
    }
    @GetMapping("/getAuthorizedUser") //при переходе на http://localhost:8080/getAuthorizedUser получаем json авторизованного юзера
    public User getAuthorizedUser() {
        User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authorizedUser;
    }
}



