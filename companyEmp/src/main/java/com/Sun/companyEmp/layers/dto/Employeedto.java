package com.Sun.companyEmp.layers.dto;

import com.Sun.companyEmp.layers.Domain.Employee;

import java.util.Objects;

public class Employeedto {
    private String name;
    private int id;
    private String address;
    private boolean is_fulltime;
    private int yearOfExp;

    public Employeedto(){

    }
    public Employeedto(int id,String name, String address, boolean is_fulltime, int yearOfExp) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.is_fulltime = is_fulltime;
        this.yearOfExp = yearOfExp;
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

    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, id, address, is_fulltime, yearOfExp);
    }

    @Override
    public String toString() {
        return "Employeedto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", is_fullTime=" + is_fulltime +
                ", yearOfExp=" + yearOfExp +
                '}';
    }
}
