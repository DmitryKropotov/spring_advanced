package com.luxoft.springadvanced.mapper;

import com.luxoft.springadvanced.dto.DivisionDTO;
import com.luxoft.springadvanced.dto.EmployeeDTO;
import com.luxoft.springadvanced.entity.Division;
import com.luxoft.springadvanced.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperUnitTest {

    EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    @DisplayName("Given an EmployeeDTO, when we map it to an Employee, then the fields with different names are correctly mapped")
    public void testMappingEmployeeDTOToEmployeeWithDifferentFieldsNames() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("John");

        Employee entity = mapper.employeeDTOtoEmployee(dto);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());
    }

    @Test
    @DisplayName("Given an Employee, when we map it to an EmployeeDTO, then the fields with different names are correctly mapped")
    public void testMappingEmployeeToEmployeeDTOWithDifferentFieldsNames() {
        Employee entity = new Employee();
        entity.setId(1);
        entity.setName("John");

        EmployeeDTO dto = mapper.employeeToEmployeeDTO(entity);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());
    }

    @Test
    @DisplayName("Given an EmployeeDTO with a nested object, when we map it to an Employee, then the fields are correctly mapped")
    public void testEmployeeDTOWithNestedObjectToEmployee() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setDivision(new DivisionDTO(1, "Division1"));

        Employee entity = mapper.employeeDTOtoEmployee(dto);

        assertEquals(dto.getDivision().getId(), entity.getDivision().getId());
        assertEquals(dto.getDivision().getName(), entity.getDivision().getName());
    }

    @Test
    @DisplayName("Given an Employee with a nested object, when we map it to an EmployeeDTO, then the fields are correctly mapped")
    public void testEmployeeWithNestedObjectToEmployeeDTO() {
        Employee entity = new Employee();
        entity.setDivision(new Division(1, "Division1"));

        EmployeeDTO dto = mapper.employeeToEmployeeDTO(entity);

        assertEquals(dto.getDivision().getId(), entity.getDivision().getId());
        assertEquals(dto.getDivision().getName(), entity.getDivision().getName());
    }

    @Test
    @DisplayName("Given a list of Employee, when we map it to a list of EmployeeDTO, then the fields with different names are correctly mapped")
    public void testEmployeeListToEmployeeDTOList() {
        List<Employee> employeeList = new ArrayList<>();
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("EmpName");
        emp.setDivision(new Division(1, "Division1"));
        employeeList.add(emp);

        List<EmployeeDTO> employeeDtoList = mapper.convertEmployeeListToEmployeeDTOList(employeeList);
        EmployeeDTO employeeDTO = employeeDtoList.get(0);
        assertEquals(employeeDTO.getEmployeeId(), emp.getId());
        assertEquals(employeeDTO.getEmployeeName(), emp.getName());
        assertEquals(employeeDTO.getDivision().getId(), emp.getDivision().getId());
        assertEquals(employeeDTO.getDivision().getName(), emp.getDivision().getName());
    }

    @Test
    @DisplayName("Given a list of EmployeeDTO, when we map it to a list of Employee, then the fields with different names are correctly mapped")
    public void testEmployeeDTOListToEmployeeList() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setEmployeeId(1);
        empDTO.setEmployeeName("EmpName");
        empDTO.setDivision(new DivisionDTO(1, "Division1"));
        employeeDTOList.add(empDTO);

        List<Employee> employeeList = mapper.convertEmployeeDTOListToEmployeeList(employeeDTOList);
        Employee employee = employeeList.get(0);
        assertEquals(employee.getId(), empDTO.getEmployeeId());
        assertEquals(employee.getName(), empDTO.getEmployeeName());
        assertEquals(employee.getDivision().getId(), empDTO.getDivision().getId());
        assertEquals(employee.getDivision().getName(), empDTO.getDivision().getName());
    }

}
