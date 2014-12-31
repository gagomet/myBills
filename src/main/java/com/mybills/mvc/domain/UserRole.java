package com.mybills.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by Padonag on 28.12.2014.
 */
@Entity
@Table(name = "client_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE", "LOGIN"}))
public class UserRole {
    private Integer userRoleId;
    private String role;
    private BillUser user;

    public UserRole(){
    }

    public UserRole(BillUser user, String role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true, nullable = false)

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "ROLE", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGIN", nullable = false)
    public BillUser getUser() {
        return user;
    }

    public void setUser(BillUser user) {
        this.user = user;
    }
}
