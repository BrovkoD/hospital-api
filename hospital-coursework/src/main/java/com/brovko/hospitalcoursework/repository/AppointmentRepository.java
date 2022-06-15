package com.brovko.hospitalcoursework.repository;

import com.brovko.hospitalcoursework.model.AppointmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentPOJO, Long> {

    List<AppointmentPOJO> findAllByAppointmentTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<AppointmentPOJO> findByDocIdAndAppointmentTime(Long docId, LocalDateTime dateTime);
}
