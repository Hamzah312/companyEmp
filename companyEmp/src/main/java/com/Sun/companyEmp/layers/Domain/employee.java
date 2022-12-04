package com.Sun.companyEmp.layers.Domain;

import java.util.Objects;

public class employee {
    private String name;
    private int id;
    private String address;
    private boolean is_fullTime;
    private int yearOfExp;

    public employee(){

    }
    public employee(String name, int id, String address, boolean is_fullTime, int yearOfExp) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.is_fullTime = is_fullTime;
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
        return is_fullTime;
    }

    public void setIs_fullTime(boolean is_fullTime) {
        this.is_fullTime = is_fullTime;
    }

    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        employee employee = (employee) o;
        return id == employee.id && is_fullTime == employee.is_fullTime && yearOfExp == employee.yearOfExp && name.equals(employee.name) && address.equals(employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, address, is_fullTime, yearOfExp);
    }
}
