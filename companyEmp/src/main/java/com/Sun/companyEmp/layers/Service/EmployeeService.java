package com.Sun.companyEmp.layers.Service;


import com.Sun.companyEmp.layers.Converter.EmployeeConverter;
import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Exceptions.DataNotFoundException;
import com.Sun.companyEmp.layers.Exceptions.SemanticException;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepo;
import com.Sun.companyEmp.layers.dto.Employeedto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    EmployeeRepo employeeRepo;
    EmployeeConverter employeeConverter;
    public EmployeeService(EmployeeRepo employeeRepo,EmployeeConverter employeeConverter)
    {
        this.employeeRepo=employeeRepo;
        this.employeeConverter=employeeConverter;
    }
    public Employeedto createEmployee(Employeedto emp){

        validate(emp);

        Employee cEmp= employeeRepo.createEmployee(employeeConverter.fromDto(emp));
        return employeeConverter.fromDomain(cEmp);
    }

    public Employeedto getEmployee(long serial_number)
    {
        checkExisting(serial_number);

        return employeeConverter.fromDomain(employeeRepo.getEmployee(serial_number));

    }
    public Employeedto updateEmployee(Long serial_number,Employeedto emp)
    {
        checkExisting(serial_number);

        validate(emp);

        Employee cEmp= employeeRepo.updateEmployee(serial_number,employeeConverter.fromDto(emp));
        return employeeConverter.fromDomain(cEmp);
    }
    public void deleteEmployee(long serial_number){
        checkExisting(serial_number);
        employeeRepo.deleteEmployee(serial_number);
    }

    private void checkExisting(Long serial_number) {
        Employee employee=employeeRepo.getEmployee(serial_number);
        if(employee != null)
            System.out.println("Done");
        else
            throw new DataNotFoundException("Employee with serial_number: "+ serial_number +" is not found");
    }

    private static void validate(Employeedto emp) {
        if(emp.getYearOfExp()<0)
            throw new SemanticException("Years of exp should be zero or more");
    }


}
