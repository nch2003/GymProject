/** Clasa pentru HomeController
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.controller;

import com.example.GymProject.models.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.GymProject.repositories.AppUserRepository;


import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final AppUserRepository userRepository;

    public HomeController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            String email = principal.getName();
            AppUser user = userRepository.findByEmail(email);

            model.addAttribute("isAdmin", user.isAdmin());
            model.addAttribute("conectat", user.getId());
            model.addAttribute("username", user.getUsername());
        } else {
            model.addAttribute("isAdmin", false);
        }
        return "index";
    }

    @GetMapping("/users")
    public String listUsers(Model model, Principal principal) {
        if (principal != null) {
            AppUser currentUser = userRepository.findByEmail(principal.getName());
            if (currentUser.isAdmin()) {
                List<AppUser> users = userRepository.findAll();
                model.addAttribute("users", users);
                model.addAttribute("isAdmin", currentUser.isAdmin());
                model.addAttribute("conectat", currentUser.getId());
            }

        }
        return "users";
    }
}
