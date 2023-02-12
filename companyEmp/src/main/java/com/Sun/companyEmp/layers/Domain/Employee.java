package com.Sun.companyEmp.layers.Domain;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "employees_serial_number_seq")
    private long serial_number;
    private String name;
    private int id;
    private String address;
    private boolean is_fulltime;
    private int year_of_exp;

    public Employee(){

    }
    public Employee(int id,String name, String address, boolean is_fulltime, int yearOfExp) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.is_fulltime = is_fulltime;
        this.year_of_exp = yearOfExp;
    }

    public long getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(long serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIs_fullTime() {
        return is_fulltime;
    }

    public void setIs_fullTime(boolean is_fullTime) {
        this.is_fulltime = is_fulltime;
    }

    public int getYear_of_exp() {
        return year_of_exp;
    }

    public void setYear_of_exp(int year_of_exp) {
        this.year_of_exp = year_of_exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && is_fulltime == employee.is_fulltime && year_of_exp == employee.year_of_exp && name.equals(employee.name) && address.equals(employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, address, is_fulltime, year_of_exp);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", is_fullTime=" + is_fulltime +
                ", yearOfExp=" + year_of_exp +
                '}';
    }
}
