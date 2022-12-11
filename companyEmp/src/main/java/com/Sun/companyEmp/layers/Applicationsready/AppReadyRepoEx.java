package com.Sun.companyEmp.layers.Applicationsready;

import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class AppReadyRepoEx {

    EmployeeRepo employeeRepo;

    public AppReadyRepoEx(EmployeeRepo employeeRepo)
    {
    this.employeeRepo =employeeRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomething()
    {
        Employee employee= new Employee(132,"Hamzeh","Bethlehem",true,4);



        System.out.println(employeeRepo.updateEmployee(1L,employee));

    }
}
