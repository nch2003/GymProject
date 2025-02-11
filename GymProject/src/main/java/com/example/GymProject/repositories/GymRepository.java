/** Repository pentru GymRepository
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.repositories;

import com.example.GymProject.models.Gyms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymRepository extends JpaRepository<Gyms, Long> {
    List<Gyms> findByNumeSalaContainingOrDescriereContaining(String numeSala, String descriere);

}
