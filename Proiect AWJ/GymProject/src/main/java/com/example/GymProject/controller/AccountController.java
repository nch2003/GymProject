/** Clasa pentru AccountController
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.controller;

import com.example.GymProject.models.AppUser;
import com.example.GymProject.models.RegisterDto;
import com.example.GymProject.repositories.AppUserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("succes", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Parolele nu se potrivesc"));
        }

        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            result.addError(new FieldError("registerDto", "email", "Adresa de email este deja utilizată"));
        }

        String password = registerDto.getPassword();
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasMinLength = password.length() >= 6;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        if (!hasMinLength || !hasUppercase || !hasDigit || !hasSpecial) {
            result.addError(new FieldError(
                    "registerDto",
                    "password",
                    "Parola trebuie să aibă minim 6 caractere, o literă mare, o cifră și un caracter special"
            ));
        }

        if (result.hasErrors()) {
            return "register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setUsername(registerDto.getUsername());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("succes", true);
        } catch (Exception ex) {
            result.addError(new FieldError("registerDto", "username", ex.getMessage()));
        }
        return "register";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (!isValidEmail(username)) {
            model.addAttribute("invalidEmail", true);
            return "login";
        }
        if (validUser(username, password)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.');
    }

    private boolean validUser(String username, String password) {
        return true;
    }

    private final AppUserRepository userRepository;

    public AccountController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal User authenticatedUser, Model model) {
        AppUser user = userRepository.findByEmail(authenticatedUser.getUsername());

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("tipAb",
                user.getTipAb() != null ? user.getTipAb() : "Nu există abonament valabil");
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("conectat", user.getId());

        return "profile";
    }



    @GetMapping("/create-subscription")
    public String createSubscriptionForm(@AuthenticationPrincipal User authenticatedUser, Model model) {
        AppUser user = userRepository.findByEmail(authenticatedUser.getUsername());


        // Adaugă datele utilizatorului în model
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("tipAb", user.getTipAb());
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("conectat", user.getId());

        return "create-subscription";
    }

    @Transactional
    @PostMapping("/create-subscription")
    public String createSubscription(@RequestParam String tipAb, Principal principal) {
        String email = principal.getName();
        userRepository.updateTipAb(email, tipAb);

        return "redirect:/profile";
    }



}
