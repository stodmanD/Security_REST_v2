package com.stolypin.securitybootstrap.controller;

import com.stolypin.securitybootstrap.model.Role;
import com.stolypin.securitybootstrap.model.User;
import com.stolypin.securitybootstrap.services.RoleService;
import com.stolypin.securitybootstrap.services.UserService;
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
    public String first () {
        return "login";
    }

    @GetMapping("/index")
    public String index () {
        return "login";
    }


    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;

    }
    @GetMapping("/user")
    public String oneUser (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

}
