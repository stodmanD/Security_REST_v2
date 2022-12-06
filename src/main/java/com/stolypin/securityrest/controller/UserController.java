package com.stolypin.securityrest.controller;

import com.stolypin.securityrest.model.User;
import com.stolypin.securityrest.services.RoleService;
import com.stolypin.securityrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String oneUser() {
        return "user";
    }
    @GetMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

}
