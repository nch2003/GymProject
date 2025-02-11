/** Repository pentru AppUserRepository
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.repositories;

import com.example.GymProject.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByEmail(String email);

    @Modifying
    @Query("UPDATE AppUser u SET u.tipAb = :tipAb WHERE u.email = :email")
    void updateTipAb(@Param("email") String email, @Param("tipAb") String tipAb);

}
