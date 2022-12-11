package com.Sun.companyEmp.layers.Config;


import com.Sun.companyEmp.layers.Converter.EmployeeConverter;
import com.Sun.companyEmp.layers.Domain.Employee;
import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

@Bean
    public RowMapper<Employee> rowMapper()
    {
        return new BeanPropertyRowMapper<>(Employee.class);
    }

@Bean
    public EmployeeConverter employeeConverter()
    {
        return new EmployeeConverter();
    }

}
