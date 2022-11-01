package com.luxoft.springadvanced.mapper;

import com.luxoft.springadvanced.dto.DivisionDTO;
import com.luxoft.springadvanced.dto.EmployeeDTO;
import com.luxoft.springadvanced.entity.Division;
import com.luxoft.springadvanced.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Mappings({ @Mapping(target = "employeeId", source = "entity.id"), @Mapping(target = "employeeName", source = "entity.name")})
    EmployeeDTO employeeToEmployeeDTO(Employee entity);

    @Mappings({ @Mapping(target = "id", source = "dto.employeeId"), @Mapping(target = "name", source = "dto.employeeName")})
    Employee employeeDTOtoEmployee(EmployeeDTO dto);

    DivisionDTO divisionToDivisionDTO(Division entity);

    Division divisionDTOtoDivision(DivisionDTO dto);

    List<Employee> convertEmployeeDTOListToEmployeeList(List<EmployeeDTO> list);

    List<EmployeeDTO> convertEmployeeListToEmployeeDTOList(List<Employee> list);

}
