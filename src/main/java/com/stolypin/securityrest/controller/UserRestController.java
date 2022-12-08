package com.stolypin.securityrest.controller;


import com.stolypin.securityrest.model.User;
import com.stolypin.securityrest.services.RoleService;
import com.stolypin.securityrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<List<User>> getAllUsers() {
        //List <User> allUser = userService.getAllUsers();
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable Integer id) {
        //User user = userService.getUser(id);

        return ResponseEntity.ok().body(userService.getUser(id));
    }

@PostMapping
    public ResponseEntity<User> addNewUser (@RequestBody User user) {
        userService.addUser(user);
    return ResponseEntity.ok().body(user);
}
    @PutMapping
    public ResponseEntity<User> editUser (@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable Integer id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchElementException("User by id" + id + "not found");
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/u")
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.ok(userService.getByUsername(principal.getName()));
    }

//    @GetMapping("/getAuthorizedUser") //при переходе на http://localhost:8080/getAuthorizedUser получаем json авторизованного юзера
//    public ResponseEntity<User> getAuthorizedUser() {
//        User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok().body(authorizedUser);
//    }
}



