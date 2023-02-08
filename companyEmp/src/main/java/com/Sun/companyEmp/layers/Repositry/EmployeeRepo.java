package com.Sun.companyEmp.layers.Repositry;



import com.Sun.companyEmp.layers.Domain.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@Repository
public class EmployeeRepo {
    private JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert ;

    RowMapper<Employee> rowMapper;
    public EmployeeRepo(JdbcTemplate jdbcTemplate , RowMapper<Employee> rowMapper)
    {
    this.jdbcTemplate=jdbcTemplate;
    this.rowMapper=rowMapper;

   simpleJdbcInsert =new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("employees").usingGeneratedKeyColumns("serial_number");
    }

    private Long createEmployeeLong(Employee emp) {



        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ID", emp.getId());
        parameters.put("name", emp.getName());
        parameters.put("ADDRESS", emp.getAddress());
        parameters.put("is_fulltime",emp.isIs_fullTime());
        parameters.put("yearOfExp",emp.getYearOfExp());


        return (Long) simpleJdbcInsert.executeAndReturnKey(parameters);

    }
    public Employee createEmployee(Employee employee){

        return getEmployee(createEmployeeLong(employee));
    }
    public Employee getEmployee(long serial_number)
    {
    try {
        Employee emp= jdbcTemplate.queryForObject("select * from employees where serial_number=?",rowMapper,serial_number);
        return emp;
    }
     catch (EmptyResultDataAccessException e)
     {
         return null;
     }
    }


    public List<Employee> getEmployees()
    {
        return jdbcTemplate.query("Select * from employees",rowMapper);
    }

    public Employee updateEmployee(long serial_number,Employee emp)
    {
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement=con.prepareStatement("UPDATE public.employees\n" +
                        "\tSET id=?, name=?, address=?, is_fulltime=?, yearofexp=?\n" +
                        "\tWHERE serial_number =?");
                preparedStatement.setInt(1,emp.getId());
                preparedStatement.setString(2,emp.getName());
                preparedStatement.setString(3,emp.getAddress());
                preparedStatement.setBoolean(4,emp.isIs_fullTime());
                preparedStatement.setInt(5,emp.getYearOfExp());
                preparedStatement.setLong(6, serial_number);

                return preparedStatement;
            }
        });

        return getEmployee(serial_number);
    }
    public void deleteEmployee(long serial_number)
    {
        jdbcTemplate.update("DELETE FROM public.employees WHERE serial_number =?",serial_number);
    }

}
