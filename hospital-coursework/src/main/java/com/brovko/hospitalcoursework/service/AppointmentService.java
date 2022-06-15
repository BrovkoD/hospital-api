package com.brovko.hospitalcoursework.service;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.AppointmentPOJO;
import com.brovko.hospitalcoursework.model.DocInfoPOJO;
import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.repository.AppointmentRepository;
import com.brovko.hospitalcoursework.repository.DocInfoRepository;
import com.brovko.hospitalcoursework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DocInfoRepository docInfoRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, DocInfoRepository docInfoRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.docInfoRepository = docInfoRepository;
    }

    public AppointmentPOJO getAppointmentById(Long id) throws LogNotFoundException {

        log.info(format("getAppointmentById method for id=%d", id));

        Optional<AppointmentPOJO> appointment = appointmentRepository.findById(id);

        if (appointment.isEmpty()) {
            throw new LogNotFoundException(format("Appointment with id=%d is not found", id));
        } else {
            return appointment.get();
        }
    }

    public List<AppointmentPOJO> getAppointmentsByDate(LocalDate startDate, LocalDate endDate) {

        if (endDate == null) {
            endDate = startDate;
        }

        log.info(format("getAppointmentsByDate method for date between %s and %s", startDate.toString(), endDate.toString()));

        return appointmentRepository.findAllByAppointmentTimeBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    public void updateAppointment(Long id, AppointmentPOJO newAppointment) throws LogNotFoundException, RoleException {

        log.info(format("updateAppointment method for id=%d", newAppointment.getId()));

        newAppointment.setId(id);

        Optional<AppointmentPOJO> appointment = appointmentRepository.findById(newAppointment.getId());
        if (appointment.isEmpty()) {
            throw new LogNotFoundException(format("Appointment with id=%d is not found", newAppointment.getId()));
        }

        validateUsers(newAppointment);

        if (!appointment.get().getAppointmentTime().equals(newAppointment.getAppointmentTime())) {
            validateTime(newAppointment);
        }

        newAppointment.setCreateTime(LocalDateTime.now());

        appointmentRepository.save(newAppointment);

    }

    public Long createAppointment(AppointmentPOJO newAppointment) throws LogNotFoundException, RoleException {

        log.info(format("createAppointment method: %s", newAppointment.toString()));

        validateUsers(newAppointment);
        validateTime(newAppointment);

        if (newAppointment.getId() != null) {
            newAppointment.setId(null);
        }

        newAppointment.setCreateTime(LocalDateTime.now());

        return appointmentRepository.save(newAppointment).getId();
    }

    public void deleteAppointment(Long id) throws LogNotFoundException {

        log.info(format("deleteAppointment method for id=%d", id));

        try {
            appointmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LogNotFoundException(format("Appointment with id=%d is not found", id));
        }
    }

    public void validateUsers(AppointmentPOJO newAppointment) throws LogNotFoundException, RoleException {

        Optional<UserPOJO> doctorOptional = userRepository.findById(newAppointment.getDocId());
        if (doctorOptional.isEmpty()) {
            throw new LogNotFoundException(format("Doctor with id=%d is not found", newAppointment.getDocId()));
        }

        UserPOJO doctor = doctorOptional.get();
        if (!doctor.getRoleName().equals("doctor")) {
            throw new RoleException(format("User with id=%d is not doctor", newAppointment.getDocId()));
        }

        DocInfoPOJO docInfo = docInfoRepository.findById(newAppointment.getDocId()).get();
        if (!docInfo.getAvailable()) {

            LocalDateTime appointmentTime = newAppointment.getAppointmentTime();
            if (appointmentTime.isAfter(docInfo.getStartDate()) && appointmentTime.isBefore(docInfo.getEndDate())) {
                throw new DateTimeException(format("Doctor with id=%d is not available for the specified time",  newAppointment.getDocId()));
            }
        }

        Optional<UserPOJO> patientOptional = userRepository.findById(newAppointment.getPatientId());
        if (patientOptional.isEmpty()) {
            throw new LogNotFoundException(format("Patient with id=%d is not found", newAppointment.getPatientId()));
        }
    }

    public void validateTime(AppointmentPOJO newAppointment) {

        Optional<AppointmentPOJO> appointment = appointmentRepository.findByDocIdAndAppointmentTime(newAppointment.getDocId(), newAppointment.getAppointmentTime());

        if (appointment.isPresent()) {
            throw new DateTimeException(format("Doctor id=%d, time is already booked", newAppointment.getDocId()));
        }
    }
}
