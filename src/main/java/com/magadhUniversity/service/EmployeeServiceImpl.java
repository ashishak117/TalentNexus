package com.magadhUniversity.service;

import com.magadhUniversity.model.Employee;
import com.magadhUniversity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public void updateEmployee(Long employeeId, String name, String address, String contactNumber, String email, String position, String department, LocalDate dateOfJoining) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setName(name);
            employee.setAddress(address);
            employee.setContactNumber(contactNumber);
            employee.setEmail(email);
            employee.setPosition(position);
            employee.setDepartment(department);
            employee.setDateOfJoining(dateOfJoining);
            employeeRepository.save(employee);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
