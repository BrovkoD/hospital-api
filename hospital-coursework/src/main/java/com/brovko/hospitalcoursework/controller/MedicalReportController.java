package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.configuration.security.MyUserDetails;
import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.MedicalReportPOJO;
import com.brovko.hospitalcoursework.service.MedicalReportService;
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
public class MedicalReportController {

    private final MedicalReportService medicalReportService;

    @Autowired
    public MedicalReportController(MedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/medical-reports/{id}")
    public ResponseEntity<MedicalReportPOJO> getMedicalReportById (@PathVariable Long id) throws LogNotFoundException {
        return ResponseEntity.ok(medicalReportService.getMedicalReportById(id));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/medical-reports")
    public ResponseEntity<List<MedicalReportPOJO>> getMedicalReportsByDate (@RequestParam("start") @DateTimeFormat(iso = DATE) LocalDate startDate,
                                                                            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DATE) LocalDate endDate) {
        return ResponseEntity.ok(medicalReportService.getMedicalReportsByDate(startDate, endDate));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @PutMapping("/medical-reports/{id}")
    public ResponseEntity<Void> updateMedicalReport(@PathVariable Long id,
                                                    @RequestBody MedicalReportPOJO newMedicalReport) throws LogNotFoundException, RoleException {
        medicalReportService.updateMedicalReport(id, newMedicalReport);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @PostMapping("/medical-reports")
    public ResponseEntity<Void> createMedicalReport(@RequestBody MedicalReportPOJO newMedicalReport) throws LogNotFoundException, RoleException {
        Long id = medicalReportService.createMedicalReport(newMedicalReport);
        return ResponseEntity.created(URI.create("/newMedicalReport/" + id)).build();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @DeleteMapping("/medical-reports/{id}")
    public ResponseEntity<Void> deleteMedicalReport(@PathVariable Long id) throws LogNotFoundException {
        medicalReportService.deleteMedicalReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
