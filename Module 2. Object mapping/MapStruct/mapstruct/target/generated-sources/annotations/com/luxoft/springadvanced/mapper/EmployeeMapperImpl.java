package com.luxoft.springadvanced.mapper;

import com.luxoft.springadvanced.dto.DivisionDTO;
import com.luxoft.springadvanced.dto.EmployeeDTO;
import com.luxoft.springadvanced.entity.Division;
import com.luxoft.springadvanced.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-01T17:22:02+0300",
    comments = "version: 1.4.0.Beta1, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO employeeToEmployeeDTO(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmployeeId( entity.getId() );
        employeeDTO.setEmployeeName( entity.getName() );
        employeeDTO.setDivision( divisionToDivisionDTO( entity.getDivision() ) );

        return employeeDTO;
    }

    @Override
    public Employee employeeDTOtoEmployee(EmployeeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( dto.getEmployeeId() );
        employee.setName( dto.getEmployeeName() );
        employee.setDivision( divisionDTOtoDivision( dto.getDivision() ) );

        return employee;
    }

    @Override
    public DivisionDTO divisionToDivisionDTO(Division entity) {
        if ( entity == null ) {
            return null;
        }

        DivisionDTO divisionDTO = new DivisionDTO();

        divisionDTO.setId( entity.getId() );
        divisionDTO.setName( entity.getName() );

        return divisionDTO;
    }

    @Override
    public Division divisionDTOtoDivision(DivisionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Division division = new Division();

        division.setId( dto.getId() );
        division.setName( dto.getName() );

        return division;
    }

    @Override
    public List<Employee> convertEmployeeDTOListToEmployeeList(List<EmployeeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Employee> list1 = new ArrayList<Employee>( list.size() );
        for ( EmployeeDTO employeeDTO : list ) {
            list1.add( employeeDTOtoEmployee( employeeDTO ) );
        }

        return list1;
    }

    @Override
    public List<EmployeeDTO> convertEmployeeListToEmployeeDTOList(List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeDTO> list1 = new ArrayList<EmployeeDTO>( list.size() );
        for ( Employee employee : list ) {
            list1.add( employeeToEmployeeDTO( employee ) );
        }

        return list1;
    }
}
