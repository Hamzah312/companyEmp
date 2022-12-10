package com.Sun.companyEmp;

import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppReady {

    EmployeeRepo employeeRepo;

    public AppReady(EmployeeRepo employeeRepo)
    {
    this.employeeRepo =employeeRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomthing()
    {
        Employee employee= new Employee(3,"Ahmed","Bethlehem",true,4);

        employeeRepo.addEmployee(employee);
        System.out.println(employeeRepo.getEmployee(1));
        System.out.println(employeeRepo.getEmployee(3));
        System.out.println(employeeRepo.getEmployee(5));

    }
}
