package com.brovko.hospitalcoursework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "doc_info")
@Entity
public class DocInfoPOJO {

    @Id
    @Column(name = "doc_id", nullable = false)
    private Long docId;

    @Column(name = "room", nullable = false)
    private Long room;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public DocInfoPOJO() {
    }

    public DocInfoPOJO(Long docId, Long room, Boolean available, LocalDateTime startDate, LocalDateTime endDate) {
        this.docId = docId;
        this.room = room;
        this.available = available;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
