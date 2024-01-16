package com.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_name")
    @Enumerated(EnumType.STRING)
    private PermissionType name;

    @Column(name = "end_point")
    private String endPoint;

    @Enumerated(EnumType.STRING)
    private MethodType method;
}
