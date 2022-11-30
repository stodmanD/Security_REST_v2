package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

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

    @GetMapping("/index")
    public String index () {
        return "index";
    }



    @GetMapping("/admin")
    public String allUser (@AuthenticationPrincipal User user, @AuthenticationPrincipal Role role, Model model) {
       model.addAttribute("user", user);
       model.addAttribute("allRoles", roleService.getAllRole());
       model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/addUser")
    public String addUser (Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "UserInfo";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String updateUser (@PathVariable("id") int id, Model model ){
        User user = userService.getUser(id);
        model.addAttribute("User", user);
        return "UserInfo";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser (@PathVariable ("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin";
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



