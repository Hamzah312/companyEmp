package com.Sun.companyEmp.layers.Controller;


import com.Sun.companyEmp.layers.Service.EmployeeService;
import com.Sun.companyEmp.layers.dto.Employeedto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService service){
        employeeService=service;
    }

   @RequestMapping(method = RequestMethod.GET,path = "/{serial_number}")
   @ResponseStatus(HttpStatus.ACCEPTED)
    public Employeedto getEmp(@PathVariable long serial_number)
    {
        return employeeService.getEmployee(serial_number);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Employeedto> getEmp()
    {
        return employeeService.getEmployees();
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{serial_number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmp(@PathVariable long serial_number)
    {
         employeeService.deleteEmployee(serial_number);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Employeedto createEmp(@RequestBody Employeedto employeedto)
    {
       return employeeService.createEmployee(employeedto);
    }

    @RequestMapping(method = RequestMethod.PUT,path = "/{serial_number}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employeedto updateEmp(@PathVariable long serial_number,@RequestBody Employeedto employeedto)
    {
        return employeeService.updateEmployee(serial_number,employeedto);
    }

}
