package com.AuthService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class UserDetail {
    private long id;
    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String hashedPassword;
    private String phoneNumber;

}
