package com.Sun.companyEmp.layers.Repositry;

import com.Sun.companyEmp.layers.Domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
