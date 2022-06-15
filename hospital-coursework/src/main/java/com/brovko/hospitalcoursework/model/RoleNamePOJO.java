package com.brovko.hospitalcoursework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "role")
@Entity
public class RoleNamePOJO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public RoleNamePOJO() {
    }

    public RoleNamePOJO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleNamePOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
