package com.example.shopberry.auth.dto;

import com.example.shopberry.user.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @JsonProperty("first_name")
    private String firstname;

    @JsonProperty("last_name")
    private String lastname;

    private String email;

    private String password;

    @JsonProperty("is_company")
    private Boolean isCompany = false;

    @JsonProperty("employee_type_id")
    private Long employeeTypeId;

    private Role role;

}
