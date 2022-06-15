package com.brovko.hospitalcoursework.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "medical_report_log")
@Entity
public class MedicalReportPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doc_id", nullable = false)
    private Long docId;

    @Column(name = "report")
    private String report;

    @Column(name = "date")
    private LocalDateTime date;

    public MedicalReportPOJO() {
    }

    public MedicalReportPOJO(Long id, Long patientId, Long docId, String report, LocalDateTime date) {
        this.id = id;
        this.patientId = patientId;
        this.docId = docId;
        this.report = report;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MedicalReportPOJO{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", docId=" + docId +
                ", report='" + report + '\'' +
                ", date=" + date +
                '}';
    }
}
