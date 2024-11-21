package com.magadhUniversity.repository;

import com.magadhUniversity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Update method to find employees by department
    List<Employee> findByDepartment(String department);
}
