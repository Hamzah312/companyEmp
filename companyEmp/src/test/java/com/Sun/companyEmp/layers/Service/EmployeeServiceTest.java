package com.Sun.companyEmp.layers.Service;

import com.Sun.companyEmp.layers.Converter.EmployeeConverter;
import com.Sun.companyEmp.layers.Domain.Employee;
import com.Sun.companyEmp.layers.Exceptions.DataNotFoundException;
import com.Sun.companyEmp.layers.Exceptions.SemanticException;
import com.Sun.companyEmp.layers.Repositry.EmployeeRepo;
import com.Sun.companyEmp.layers.dto.Employeedto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeRepo employeeRepo;
    @Mock
    EmployeeConverter employeeConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployee_employeeExist_shouldReturnEmployee() {

        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(555, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.createEmployee(employee)).thenReturn(new Employee(555, "Hamzeh", "Bethlehem", true, 4));

        Employeedto employeedto = employeeService.createEmployee(new Employeedto(555, "Hamzeh", "Bethlehem", true, 4));

        assertEquals("Hamzeh", employeedto.getName());
        assertEquals(555, employeedto.getId());


    }

    @Test
    void createEmployee_EmployeeExist_shouldThrowSE() {
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.createEmployee(employee)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        SemanticException semanticException = assertThrows(SemanticException.class, () -> employeeService.createEmployee(new Employeedto(0, "Hamzeh", "Bethlehem", true, 4)));

        assertEquals("Years of exp should be zero or more or/and id should be positive", semanticException.getMessage());
        verify(employeeRepo, never()).createEmployee(any());
        verify(employeeConverter, never()).fromDomain(any());
        verify(employeeConverter, never()).fromDto(any());
    }

    @Test
    void createEmployee_EmployeeExist_shouldThrowSE2() {
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.createEmployee(employee)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        SemanticException semanticException = assertThrows(SemanticException.class, () -> employeeService.createEmployee(new Employeedto(132, "Hamzeh", "Bethlehem", true, -4)));

        assertEquals("Years of exp should be zero or more or/and id should be positive", semanticException.getMessage());
        verify(employeeRepo, never()).createEmployee(any());
        verify(employeeConverter, never()).fromDomain(any());
        verify(employeeConverter, never()).fromDto(any());
    }

    @Test
    void getEmployee_employeeExist_shouldReturnEmployee() {

        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeRepo.getEmployee(2L)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        Employeedto employeedto = employeeService.getEmployee(2L);

        assertEquals("Hamzeh", employeedto.getName());
        assertEquals(132, employeedto.getId());
        assertEquals("Bethlehem", employeedto.getAddress());
        assertTrue(employeedto.isIs_fullTime());

    }

    @Test
    void getEmployee_EmployeeNotExist_ShouldThrowDNFE() {


        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> employeeService.getEmployee(5l));

        assertEquals("can not found the particular serial_number number", dataNotFoundException.getMessage());
    }

    @Test
    void getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        Employee employee2 = new Employee(133, "Sami", "Jerusalem", false, 3);
        Employee employee3 = new Employee(134, "David", "Haifa", true, 2);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        when(employeeRepo.getEmployees()).thenReturn(employees);

        ArrayList<Employeedto> employees_dto = (ArrayList<Employeedto>) employeeService.getEmployees();
        assertEquals("Hamzeh", employees_dto.get(0).getName());
        assertEquals("Sami", employees_dto.get(1).getName());
        assertEquals("David", employees_dto.get(2).getName());
    }

    @Test
    void updateEmployee_EmployeeExist_shouldReturnEmployee() {
        when(employeeRepo.getEmployee(2L)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.updateEmployee(2L, employee)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        Employeedto employeedto = employeeService.updateEmployee(2l, new Employeedto(132, "Hamzeh", "Bethlehem", true, 4));
        assertNotNull(employeedto);
        assertEquals(132, employeedto.getId());
        assertEquals("Bethlehem", employeedto.getAddress());
    }

    @Test
    void updateEmployee_NotEmployeeExist_shouldThrowDNFE() {


        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> employeeService.updateEmployee(2l, new Employeedto(132, "Hamzeh", "Bethlehem", true, 4)));

        assertEquals("can not found the particular serial_number number", dataNotFoundException.getMessage());
        verify(employeeRepo, never()).updateEmployee(anyLong(), any());
        verify(employeeRepo).getEmployee(anyLong());
        verify(employeeConverter, never()).fromDomain(any());
        verify(employeeConverter, never()).fromDto(any());
    }

    @Test
    void updateEmployee_EmployeeExist_shouldThrowSE() {
        when(employeeRepo.getEmployee(2L)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.updateEmployee(2L, employee)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        SemanticException semanticException = assertThrows(SemanticException.class, () -> employeeService.updateEmployee(2l, new Employeedto(0, "Hamzeh", "Bethlehem", true, 4)));

        assertEquals("Years of exp should be zero or more or/and id should be positive", semanticException.getMessage());
        verify(employeeRepo, never()).updateEmployee(anyLong(), any());
        verify(employeeRepo).getEmployee(anyLong());
        verify(employeeConverter, never()).fromDomain(any());
        verify(employeeConverter, never()).fromDto(any());
    }

    @Test
    void updateEmployee_EmployeeExist_shouldThrowSE2() {
        when(employeeRepo.getEmployee(2L)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));
        when(employeeConverter.fromDomain(any())).thenCallRealMethod();
        when(employeeConverter.fromDto(any())).thenCallRealMethod();
        Employee employee = new Employee(132, "Hamzeh", "Bethlehem", true, 4);
        when(employeeRepo.updateEmployee(2L, employee)).thenReturn(new Employee(132, "Hamzeh", "Bethlehem", true, 4));

        SemanticException semanticException = assertThrows(SemanticException.class, () -> employeeService.updateEmployee(2l, new Employeedto(524, "Hamzeh", "Bethlehem", true, -2)));

        assertEquals("Years of exp should be zero or more or/and id should be positive", semanticException.getMessage());
        verify(employeeRepo, never()).updateEmployee(anyLong(), any());
        verify(employeeRepo).getEmployee(anyLong());
        verify(employeeConverter, never()).fromDomain(any());
        verify(employeeConverter, never()).fromDto(any());
    }

    @Test
    void deleteEmployee_NotExist_ThrowDNFE() {
        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> employeeService.deleteEmployee(2l));

        assertEquals("can not found the particular serial_number number", dataNotFoundException.getMessage());
        verify(employeeRepo, never()).deleteEmployee(anyLong());

    }
}
