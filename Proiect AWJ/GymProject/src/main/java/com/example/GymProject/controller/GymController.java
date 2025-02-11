/** Clasa pentru GymController
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.controller;

import com.example.GymProject.models.AppUser;
import com.example.GymProject.models.GymDTO;
import com.example.GymProject.models.Gyms;
import com.example.GymProject.repositories.AppUserRepository;
import com.example.GymProject.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class GymController {

    private final GymRepository gymRepository;

    public GymController(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Autowired
    private AppUserRepository userRepository;

    @GetMapping("/gyms")
    public String listGyms(@RequestParam(value = "search", required = false) String search, Model model, Principal principal) {
        String userSubscriptionType = null;
        if (principal != null) {
            AppUser user = userRepository.findByEmail(principal.getName());
            userSubscriptionType = user.getTipAb();
            model.addAttribute("isAdmin", user.isAdmin());
            model.addAttribute("conectat", user.getId());
        } else {
            model.addAttribute("isAdmin", false);
        }

        List<Gyms> gyms;
        if (search != null && !search.isEmpty()) {
            gyms = gymRepository.findByNumeSalaContainingOrDescriereContaining(search, search);
        } else {
            gyms = gymRepository.findAll();
        }

        model.addAttribute("gyms", gyms);
        model.addAttribute("userSubscriptionType", userSubscriptionType);
        return "gyms";
    }



    @GetMapping("/add_gym")
    public String showAddGymForm(Model model, Principal principal) {
        model.addAttribute("gymDTO", new GymDTO());
        AppUser user = userRepository.findByEmail(principal.getName());
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("conectat", user.getId());
        return "add_gym";
    }


    @PostMapping("/add_gym")
    public String addGym(@ModelAttribute("gymDTO") GymDTO gymDTO, BindingResult bindingResult, Model model, Principal principal) {
        String numeSala = gymDTO.getNumeSala();
        List<String> descriere = gymDTO.getDescriere();
        String tipAb = gymDTO.getTipAb();

        if (principal != null) {
            AppUser user = userRepository.findByEmail(principal.getName());
            model.addAttribute("isAdmin", user.isAdmin());
            model.addAttribute("conectat", user.getId());
        }

        if (numeSala == null || numeSala.isEmpty()) {
            bindingResult.rejectValue("numeSala", "error.numeSala", "Numele sălii nu poate fi gol");
        }

        if (descriere == null || descriere.isEmpty()) {
            bindingResult.rejectValue("descriere", "error.descriere", "Descrierea trebuie selectată");
        }

        if (tipAb == null || (!tipAb.equals("full-time") && !tipAb.equals("day-time"))) {
            bindingResult.rejectValue("tipAb", "error.tipAb", "Tipul abonamentului este invalid");
        }

        if (bindingResult.hasErrors()) {
            return "add_gym";
        }

        Gyms newGym = new Gyms();
        newGym.setNumeSala(numeSala);
        newGym.setDescriere(String.join(", ", descriere));
        newGym.setTipAb(tipAb);

        gymRepository.save(newGym);

        return "redirect:/gyms";
    }

    @PostMapping("/delete_gym/{id}")
    public String deleteGym(@PathVariable("id") Long id) {
        gymRepository.deleteById(id);
        return "redirect:/gyms";
    }


}
