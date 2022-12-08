package com.stolypin.securityrest.controller;

import com.stolypin.securityrest.model.Role;
import com.stolypin.securityrest.model.User;
import com.stolypin.securityrest.services.RoleService;
import com.stolypin.securityrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String first() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";

    }

    @GetMapping("/user")
    public String oneUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }
    @GetMapping(value = "/admin")
    public String adminPage(@AuthenticationPrincipal User user, @AuthenticationPrincipal Role role, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRole());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }
    @GetMapping("/us")
    public ResponseEntity<User> getUser(Principal principal) {
        return new ResponseEntity<>(userService.getByUsername(principal.getName()), HttpStatus.OK);
    }

}
