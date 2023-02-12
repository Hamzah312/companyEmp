package com.Sun.companyEmp.layers.Service;

import com.Sun.companyEmp.layers.Converter.EmployeeConverter;
import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Exceptions.DataNotFoundException;
import com.Sun.companyEmp.layers.Exceptions.SemanticException;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepository;
import com.Sun.companyEmp.layers.dto.Employeedto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {



    EmployeeRepository employeeRepository;
    EmployeeConverter employeeConverter;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    public Employeedto createEmployee(Employeedto emp) {

        validate(emp);

        Employee cEmp = (Employee) employeeRepository.save(employeeConverter.fromDto(emp));

        return employeeConverter.fromDomain(cEmp);
    }

    public Employeedto getEmployee(long serial_number) {
        checkExisting(serial_number);

        return employeeConverter.fromDomain((Employee) employeeRepository.findById(serial_number).get());

    }

    public List<Employeedto> getEmployees() {
        return  employeeRepository.findAll().stream().map(e -> employeeConverter.fromDomain((Employee) e)).collect(Collectors.toList());
    }

    public Employeedto updateEmployee(Long serial_number, Employeedto emp) {
        checkExisting(serial_number);

        validate(emp);

        return employeeRepository.findById(serial_number).map(empp ->{
            Employee foundEmployee=employeeConverter.fromDto(emp);
            foundEmployee.setSerial_number(empp.getSerial_number());
           return employeeRepository.save(foundEmployee);

        }).map(savedEmp->employeeConverter.fromDomain(savedEmp)).get();


    }

    public void deleteEmployee(long serial_number) {
        checkExisting(serial_number);
        employeeRepository.deleteById(serial_number);
    }

    private void checkExisting(Long serial_number) {
       employeeRepository.findById(serial_number).map(e -> {
            return e;
        }).orElseThrow(()-> new DataNotFoundException("can not found the particular serial_number number"));
    }

    private static void validate(Employeedto emp) {
        if (emp.getId() < 1 || emp.getYearOfExp() < 0)
            throw new SemanticException("Years of exp should be zero or more or/and id should be positive");
    }

}
