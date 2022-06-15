package com.brovko.hospitalcoursework.model;

import javax.persistence.*;

@Table(name = "user_role")
@Entity
public class UserRolePOJO {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleNamePOJO roleNameObject;

    public UserRolePOJO() {
    }

    public UserRolePOJO(Long userId, RoleNamePOJO roleNameObject) {
        this.userId = userId;
        this.roleNameObject = roleNameObject;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RoleNamePOJO getRoleNameObject() {
        return roleNameObject;
    }

    public void setRoleNameObject(RoleNamePOJO roleNameObject) {
        this.roleNameObject = roleNameObject;
    }

    @Override
    public String toString() {
        return "UserRolePOJO{" +
                "userId=" + userId +
                ", roleNameObject=" + roleNameObject +
                '}';
    }
}
