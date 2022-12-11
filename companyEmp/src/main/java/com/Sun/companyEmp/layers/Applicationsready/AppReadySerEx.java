package com.Sun.companyEmp.layers.Applicationsready;

import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Exceptions.AppException;
import com.Sun.companyEmp.layers.Service.EmployeeService;
import com.Sun.companyEmp.layers.dto.Employeedto;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadySerEx {
    private EmployeeService employeeService;

    public AppReadySerEx(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void doSomething()
    {
        Employeedto employee= new Employeedto(132,"Akram","Bethlehem",true,-4);


    try {
        System.out.println(employeeService.createEmployee(employee));
        System.out.println(employeeService.getEmployee(2L));
    }catch (AppException e)
    {
        System.out.println(e.getMessage());
    }

    }
}
