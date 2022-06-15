package com.brovko.hospitalcoursework.repository;

import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.model.UserRolePOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolePOJO, Long> {
}
