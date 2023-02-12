package com.Sun.companyEmp.layers.Applicationsready;

import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadySerEx {

    private final EmployeeRepository employeeRepository;

    public AppReadySerEx(EmployeeRepository employeeRepository)
    {
        this.employeeRepository=employeeRepository;

    }
    @EventListener(ApplicationReadyEvent.class)
    public void doSomething()
    {

        Employee employee=  employeeRepository.findById(6l).get();
        System.out.println("hhhh"+employee);
        //Employeedto employee= new Employeedto(132,"Akram","Bethlehem",true,-4);



    }
}
