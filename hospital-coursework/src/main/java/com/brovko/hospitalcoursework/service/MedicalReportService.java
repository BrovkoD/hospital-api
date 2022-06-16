package com.brovko.hospitalcoursework.service;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.MedicalReportPOJO;
import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.repository.MedicalReportRepository;
import com.brovko.hospitalcoursework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
public class MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;
    private final UserRepository userRepository;

    @Autowired
    public MedicalReportService(MedicalReportRepository medicalReportRepository, UserRepository userRepository) {
        this.medicalReportRepository = medicalReportRepository;
        this.userRepository = userRepository;
    }

    public MedicalReportPOJO getMedicalReportById(Long id) throws LogNotFoundException {

        log.info(format("getMedicalReportById method for id=%d", id));

        Optional<MedicalReportPOJO> medicalReport = medicalReportRepository.findById(id);

        if (medicalReport.isEmpty()) {
            throw new LogNotFoundException(format("Medical report with id=%d is not found", id));
        } else {
            return medicalReport.get();
        }
    }

    public List<MedicalReportPOJO> getMedicalReportsByDate(LocalDate startDate, LocalDate endDate) {

        if (endDate == null) {
            endDate = startDate;
        }

        log.info(format("getMedicalReportsByDate method for date between %s and %s", startDate, endDate));

        return medicalReportRepository.findAllByDateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    public void updateMedicalReport(Long id, MedicalReportPOJO newMedicalReport) throws LogNotFoundException, RoleException {

        log.info(format("updateMedicalReport method for id=%d", id));

        newMedicalReport.setId(id);

        Optional<MedicalReportPOJO> medicalReport = medicalReportRepository.findById(newMedicalReport.getId());
        if (medicalReport.isEmpty()) {
            throw new LogNotFoundException(format("Medical report with id=%d is not found", newMedicalReport.getId()));
        }

        validateUsers(newMedicalReport);

        newMedicalReport.setDate(LocalDateTime.now());

        medicalReportRepository.save(newMedicalReport);
    }

    public Long createMedicalReport(MedicalReportPOJO newMedicalReport) throws LogNotFoundException, RoleException {

        log.info(format("createMedicalReport method: %s", newMedicalReport.toString()));

        validateUsers(newMedicalReport);

        if (newMedicalReport.getId() != null) {
            newMedicalReport.setId(null);
        }

        newMedicalReport.setDate(LocalDateTime.now());

        return medicalReportRepository.save(newMedicalReport).getId();
    }

    public void deleteMedicalReport(Long id) throws LogNotFoundException {

        log.info(format("deleteMedicalReport method for id=%d", id));

        try {
            medicalReportRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LogNotFoundException(format("Medical report with id=%d is not found", id));
        }
    }

    public void validateUsers(MedicalReportPOJO newMedicalReport) throws LogNotFoundException, RoleException {

        Optional<UserPOJO> doctorOptional = userRepository.findById(newMedicalReport.getDocId());
        if (doctorOptional.isEmpty()) {
            throw new LogNotFoundException(format("Doctor with id=%d is not found", newMedicalReport.getDocId()));
        }

        UserPOJO doctor = doctorOptional.get();
        if (!doctor.getRoleName().equals("doctor")) {
            throw new RoleException(format("User with id=%d is not doctor", newMedicalReport.getDocId()));
        }

        Optional<UserPOJO> patientOptional = userRepository.findById(newMedicalReport.getPatientId());
        if (patientOptional.isEmpty()) {
            throw new LogNotFoundException(format("Patient with id=%d is not found", newMedicalReport.getPatientId()));
        }
    }
}
