package com.medical.securityservice.models.entities;

import com.medical.securityservice.models.enums.Role;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private String surname;
    @Column
    private String phone;
    @Column
    private Date birthday;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}
