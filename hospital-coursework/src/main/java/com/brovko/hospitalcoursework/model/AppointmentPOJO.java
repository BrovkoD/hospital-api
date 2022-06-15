package com.brovko.hospitalcoursework.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "appointment_log")
@Entity
public class AppointmentPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "doc_id", nullable = false)
    private Long docId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "price")
    private Long price;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;


    public AppointmentPOJO() {
    }

    public AppointmentPOJO(Long id, Long docId, Long patientId, Long price, LocalDateTime createTime, LocalDateTime appointmentTime) {
        this.id = id;
        this.docId = docId;
        this.patientId = patientId;
        this.price = price;
        this.createTime = createTime;
        this.appointmentTime = appointmentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "AppointmentPOJO{" +
                "id=" + id +
                ", docId=" + docId +
                ", patientId=" + patientId +
                ", price=" + price +
                ", createTime=" + createTime +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
