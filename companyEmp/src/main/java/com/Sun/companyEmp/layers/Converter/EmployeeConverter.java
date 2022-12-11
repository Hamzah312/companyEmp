package com.Sun.companyEmp.layers.Converter;

import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.dto.Employeedto;

public class EmployeeConverter {

    public Employee fromDto(Employeedto emp)
    {
        return new Employee(emp.getId(),emp.getName(),emp.getAddress(),emp.isIs_fullTime(),emp.getYearOfExp());
    }

    public Employeedto fromDomain(Employee emp)
    {
        return new Employeedto(emp.getId(),emp.getName(),emp.getAddress(),emp.isIs_fullTime(),emp.getYearOfExp());
    }

}
