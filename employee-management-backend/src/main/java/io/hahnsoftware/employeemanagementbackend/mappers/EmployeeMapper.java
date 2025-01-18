package io.hahnsoftware.employeemanagementbackend.mappers;

import io.hahnsoftware.employeemanagementbackend.dtos.EmployeeDto;
import io.hahnsoftware.employeemanagementbackend.entities.Address;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import io.hahnsoftware.employeemanagementbackend.entities.Employee;
import io.hahnsoftware.employeemanagementbackend.enums.EmploymentStatus;

public class EmployeeMapper {
    public static EmployeeDto toDto(Employee employee) {
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
                .address(String.format("%s, %s, %s, %s, %s",
                        employee.getAddress().getStreet(),
                        employee.getAddress().getCity(),
                        employee.getAddress().getState(),
                        employee.getAddress().getPostalCode(),
                        employee.getAddress().getCountry()))
                .build();
    }

    public static Employee mapToEntity(EmployeeDto employeeDto, Department department) {
        Address address = parseAddress(employeeDto.getAddress());

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

    private static Address parseAddress(String addressString) {
        if (addressString == null || addressString.isEmpty()) return null;
        String[] parts = addressString.split(", ");

        return Address.builder()
                .street(parts[0])
                .city(parts[1])
                .state(parts[2])
                .postalCode(parts[3])
                .country(parts[4])
                .build();
    }
}
