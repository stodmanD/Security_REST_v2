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

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String listUsers(@AuthenticationPrincipal User user, @AuthenticationPrincipal Role role, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRole());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin/create")
    public String create(@ModelAttribute User user, @RequestParam(value = "newRoles") String[] editRoles) throws Exception {
        Set<Role> roleSet = new HashSet<>();
        for (String role : editRoles) {
            roleSet.add(roleService.getByName(role));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @PutMapping(value = "/edit/{id}")
    public String update(@ModelAttribute User user, @RequestParam(value = "editRoles") String[] editRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String rolesAdd : editRoles) {
            roleSet.add(roleService.findByRole(rolesAdd));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
