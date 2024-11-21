package com.magadhUniversity.service;

import com.magadhUniversity.model.Employee;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long employeeId);
    void updateEmployee(Long employeeId, String name, String address, String contactNumber, String email, String position, String department, LocalDate dateOfJoining);
    void deleteEmployee(Long employeeId);
    void addEmployee(Employee employee);
}
