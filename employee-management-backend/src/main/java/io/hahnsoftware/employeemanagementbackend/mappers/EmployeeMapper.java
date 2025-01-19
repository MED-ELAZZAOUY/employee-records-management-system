package io.hahnsoftware.employeemanagementbackend.mappers;

import io.hahnsoftware.employeemanagementbackend.dtos.AddressDto;
import io.hahnsoftware.employeemanagementbackend.dtos.EmployeeDto;
import io.hahnsoftware.employeemanagementbackend.entities.Address;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import io.hahnsoftware.employeemanagementbackend.entities.Employee;
import io.hahnsoftware.employeemanagementbackend.enums.EmploymentStatus;

public class EmployeeMapper {
    public static EmployeeDto mapToDto(Employee employee) {
        AddressDto addressDto = AddressDto.builder()
                .street(employee.getAddress().getStreet())
                .city(employee.getAddress().getCity())
                .state(employee.getAddress().getState())
                .postalCode(employee.getAddress().getPostalCode())
                .country(employee.getAddress().getCountry())
                .build();

        return EmployeeDto.builder()
                .id(employee.getId())
                .employeeId(employee.getEmployeeId())
                .fullName(employee.getFullName())
                .jobTitle(employee.getJobTitle())
                .departmentName(employee.getDepartment().getName())
                .hireDate(employee.getHireDate())
                .employmentStatus(employee.getEmploymentStatus().name())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(addressDto)
                .build();
    }

    public static Employee mapToEntity(EmployeeDto employeeDto, Department department) {
        Address address = Address.builder()
                .street(employeeDto.getAddress().getStreet())
                .city(employeeDto.getAddress().getCity())
                .state(employeeDto.getAddress().getState())
                .postalCode(employeeDto.getAddress().getPostalCode())
                .country(employeeDto.getAddress().getCountry())
                .build();

        Employee.EmployeeBuilder builder = Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .fullName(employeeDto.getFullName())
                .jobTitle(employeeDto.getJobTitle())
                .department(department)
                .hireDate(employeeDto.getHireDate())
                .employmentStatus(EmploymentStatus.valueOf(employeeDto.getEmploymentStatus()))
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .address(address);

        if (employeeDto.getId() != null) {
            builder.id(employeeDto.getId());
        }

        return builder.build();
    }
}
