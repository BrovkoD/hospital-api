package com.brovko.hospitalcoursework.repository;

import com.brovko.hospitalcoursework.model.MedicalReportPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReportPOJO, Long> {

    List<MedicalReportPOJO> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}