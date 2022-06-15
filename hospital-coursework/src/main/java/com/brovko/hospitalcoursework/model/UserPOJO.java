package com.brovko.hospitalcoursework.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "users_list")
@Entity
public class UserPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "password")
    private String password;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private UserRolePOJO userRoleObject;

    public UserPOJO() {
    }

    public UserPOJO(Long id, String firstName, String lastName, String email, String phoneNum, String password, LocalDateTime createTime, UserRolePOJO userRoleObject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.createTime = createTime;
        this.userRoleObject = userRoleObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public UserRolePOJO getUserRoleObject() {
        return userRoleObject;
    }

    public void setUserRoleObject(UserRolePOJO userRoleObject) {
        this.userRoleObject = userRoleObject;
    }

    public String getRoleName() {
        return userRoleObject.getRoleNameObject().getName();
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", createTime=" + createTime +
                ", userRoleObject=" + userRoleObject +
                '}';
    }
}
