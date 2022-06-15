package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.AppointmentPOJO;
import com.brovko.hospitalcoursework.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/appointments/{id}")
    public ResponseEntity<AppointmentPOJO> getAppointmentById (@PathVariable Long id) throws LogNotFoundException {

        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentPOJO>> getAppointmentsByDate (@RequestParam @DateTimeFormat(iso = DATE) LocalDate startDate,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DATE) LocalDate endDate) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDate(startDate, endDate));
    }

    @PutMapping("/updateAppointment/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id,
                                                  @RequestBody AppointmentPOJO newAppointment) throws LogNotFoundException, RoleException {
        appointmentService.updateAppointment(id, newAppointment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/newAppointment")
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentPOJO newAppointment) throws LogNotFoundException, RoleException {
        Long id = appointmentService.createAppointment(newAppointment);
        return ResponseEntity.created(URI.create("/newAppointment/" + id)).build();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) throws LogNotFoundException {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
