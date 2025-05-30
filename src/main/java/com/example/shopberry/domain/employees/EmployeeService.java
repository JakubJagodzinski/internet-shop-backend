package com.example.shopberry.domain.employees;

import com.example.shopberry.auth.dto.RegisterRequestDto;
import com.example.shopberry.domain.employees.dto.EmployeeDtoMapper;
import com.example.shopberry.domain.employees.dto.EmployeeResponseDto;
import com.example.shopberry.domain.employees.dto.UpdateEmployeeRequestDto;
import com.example.shopberry.domain.employeetypes.EmployeeType;
import com.example.shopberry.domain.employeetypes.EmployeeTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeTypeRepository employeeTypeRepository;

    private final EmployeeDtoMapper employeeDtoMapper;

    private final PasswordEncoder passwordEncoder;

    private final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found";
    private final String EMPLOYEE_TYPE_NOT_FOUND_MESSAGE = "Employee type not found";
    private final String EMPLOYEE_WITH_THAT_EMAIL_ALREADY_EXISTS_MESSAGE = "Employee with that email already exists";
    private final String EMPLOYEE_FIRST_NAME_CANNOT_BE_EMPTY_MESSAGE = "Employee first name cannot be empty";
    private final String EMPLOYEE_LAST_NAME_CANNOT_BE_EMPTY_MESSAGE = "Employee last name cannot be empty";
    private final String EMPLOYEE_EMAIL_CANNOT_BE_EMPTY_MESSAGE = "Employee email cannot be empty";
    private final String EMPLOYEE_PASSWORD_CANNOT_BE_EMPTY_MESSAGE = "Employee password cannot be empty";

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeTypeRepository employeeTypeRepository, PasswordEncoder passwordEncoder, EmployeeDtoMapper employeeDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeTypeRepository = employeeTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeDtoMapper = employeeDtoMapper;
    }

    public List<EmployeeResponseDto> getEmployees() {
        return employeeDtoMapper.toDtoList(employeeRepository.findAll());
    }

    @Transactional
    public EmployeeResponseDto getEmployeeById(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
        }

        return employeeDtoMapper.toDto(employee);
    }

    @Transactional
    public Employee register(RegisterRequestDto registerRequestDto) throws EntityNotFoundException, IllegalArgumentException {
        EmployeeType employeeType = employeeTypeRepository.findById(registerRequestDto.getEmployeeTypeId()).orElse(null);

        if (employeeType == null) {
            throw new EntityNotFoundException(EMPLOYEE_TYPE_NOT_FOUND_MESSAGE);
        }

        Employee employee = new Employee();

        employee.setFirstName(registerRequestDto.getFirstname());
        employee.setLastName(registerRequestDto.getLastname());
        employee.setEmail(registerRequestDto.getEmail());
        employee.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        employee.setEmployeeType(employeeType);

        return employeeRepository.save(employee);
    }

    @Transactional
    public EmployeeResponseDto updateEmployeeById(Long id, UpdateEmployeeRequestDto updateEmployeeRequestDto) throws EntityNotFoundException, IllegalArgumentException {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
        }

        if (updateEmployeeRequestDto.getEmployeeTypeId() != null) {
            EmployeeType employeeType = employeeTypeRepository.findById(updateEmployeeRequestDto.getEmployeeTypeId()).orElse(null);

            if (employeeType == null) {
                throw new EntityNotFoundException(EMPLOYEE_TYPE_NOT_FOUND_MESSAGE);
            }

            employee.setEmployeeType(employeeType);
        }

        if (updateEmployeeRequestDto.getFirstName() != null) {
            if (updateEmployeeRequestDto.getFirstName().isEmpty()) {
                throw new IllegalArgumentException(EMPLOYEE_FIRST_NAME_CANNOT_BE_EMPTY_MESSAGE);
            }

            employee.setFirstName(updateEmployeeRequestDto.getFirstName());
        }

        if (updateEmployeeRequestDto.getLastName() != null) {
            if (updateEmployeeRequestDto.getLastName().isEmpty()) {
                throw new IllegalArgumentException(EMPLOYEE_LAST_NAME_CANNOT_BE_EMPTY_MESSAGE);
            }

            employee.setLastName(updateEmployeeRequestDto.getLastName());
        }

//        if (updateEmployeeRequestDto.getEmail() != null) {
//            Employee otherEmployee = employeeRepository.findByEmail(updateEmployeeRequestDto.getEmail());
//
//            if (otherEmployee != null && !employee.getId().equals(otherEmployee.getId())) {
//                throw new IllegalArgumentException(EMPLOYEE_WITH_THAT_EMAIL_ALREADY_EXISTS_MESSAGE);
//            }
//
//            if (updateEmployeeRequestDto.getEmail().isEmpty()) {
//                throw new IllegalArgumentException(EMPLOYEE_EMAIL_CANNOT_BE_EMPTY_MESSAGE);
//            }
//
//            employee.setEmail(updateEmployeeRequestDto.getEmail());
//        }

        if (updateEmployeeRequestDto.getPassword() != null) {
            if (updateEmployeeRequestDto.getPassword().isEmpty()) {
                throw new IllegalArgumentException(EMPLOYEE_PASSWORD_CANNOT_BE_EMPTY_MESSAGE);
            }

            employee.setPassword(updateEmployeeRequestDto.getPassword());
        }

        return employeeDtoMapper.toDto(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployeeById(Long id) throws EntityNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
        }

        employeeRepository.deleteById(id);
    }

}
