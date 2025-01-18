package io.hahnsoftware.employeemanagementbackend.mappers;

import io.hahnsoftware.employeemanagementbackend.dtos.DepartmentDto;
import io.hahnsoftware.employeemanagementbackend.entities.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static Department mapToEntity(DepartmentDto departmentDto) {
         Department.DepartmentBuilder builder = Department.builder()
                .name(departmentDto.getName());

         if (departmentDto.getId() != null) {
                builder.id(departmentDto.getId());
         }

         return builder.build();
    }
}
