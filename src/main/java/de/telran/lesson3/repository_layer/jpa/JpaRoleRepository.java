package de.telran.lesson3.repository_layer.jpa;

import de.telran.lesson3.domain_layer.entity.jpa.JpaRole;
import de.telran.lesson3.domain_layer.entity.jpa.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<JpaRole, Integer> {
}