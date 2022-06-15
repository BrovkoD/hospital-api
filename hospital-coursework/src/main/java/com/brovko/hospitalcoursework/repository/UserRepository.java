package com.brovko.hospitalcoursework.repository;

import com.brovko.hospitalcoursework.model.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPOJO, Long> {

    Optional<UserPOJO> findByEmail(String email);
}
