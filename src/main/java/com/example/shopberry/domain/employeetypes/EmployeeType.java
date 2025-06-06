package com.example.shopberry.domain.employeetypes;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee_types")
public class EmployeeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_type_id")
    private Long employeeTypeId;

    @Column(name = "employee_type_name", unique = true, nullable = false, length = 100)
    private String employeeTypeName;

}
