package com.Sun.companyEmp.layers.Repositry;


import com.Sun.companyEmp.layers.Domain.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepo {
    private JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert ;
    public EmployeeRepo(JdbcTemplate jdbcTemplate)
    {
    this.jdbcTemplate=jdbcTemplate;
    System.out.println(jdbcTemplate);
    simpleJdbcInsert =
            new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("employees");
    }

    public int addEmployee(Employee emp) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ID", emp.getId());
        parameters.put("name", emp.getName());
        parameters.put("ADDRESS", emp.getAddress());
        parameters.put("is_fulltime",emp.isIs_fullTime());
        parameters.put("yearOfExp",emp.getYearOfExp());
        return simpleJdbcInsert.execute(parameters);
    }
    public int addEmployee(int id)
    {
        return jdbcTemplate.update(
                "INSERT INTO employees VALUES (?, ?, ?, ?, ?)", id, "Dawod","Bethlehem",true,4);
    }
    public Employee getEmployee(int id)
    {
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);

     Employee emp= jdbcTemplate.queryForObject("select * from employees where id=?",rowMapper,id);
    return emp;
    }

}
