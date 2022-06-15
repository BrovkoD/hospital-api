package com.brovko.hospitalcoursework.repository;

import com.brovko.hospitalcoursework.model.RoleNamePOJO;
import com.brovko.hospitalcoursework.model.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleNameRepository extends JpaRepository<RoleNamePOJO, Long> {

    Optional<RoleNamePOJO> findByName(String name);
}
