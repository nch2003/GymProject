/** Clasa pentru AppUserService
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.services;

import com.example.GymProject.models.AppUser;
import com.example.GymProject.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = repo.findByEmail(email);

        if(appUser != null) {
            var springUser = User.withUsername(appUser.getEmail()).password(appUser.getPassword()).build();
            return springUser;
        }
        return null;
    }


}
