package com.magadhUniversity.controller;

import com.magadhUniversity.model.Employee;
import com.magadhUniversity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "list_employees";
    }

    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create_employee";
    }

    @PostMapping
    public String createEmployee(Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/view/{employeeId}")
    public String viewEmployee(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        return "view_employee";
    }

    @GetMapping("/update/{employeeId}")
    public String updateEmployeeForm(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

//    @PostMapping("/update/{employeeId}")
//    public String updateEmployee(@PathVariable Long employeeId, @RequestParam String name, @RequestParam String address, @RequestParam String contactNumber, @RequestParam String email, @RequestParam String position, @RequestParam String department, @RequestParam String dateOfJoining) {
//        employeeService.updateEmployee(employeeId, name, address, contactNumber, email, position, department, LocalDate.parse(dateOfJoining));
//        return "redirect:/employees";
//    }

    @PostMapping("/update/{employeeId}")
    public String updateEmployee(@PathVariable Long employeeId,
                                 @RequestParam String name,
                                 @RequestParam String address,
                                 @RequestParam String contactNumber,
                                 @RequestParam String email,
                                 @RequestParam String position,
                                 @RequestParam String department,
                                 @RequestParam(required = false) String dateOfJoining) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        // Only update the date if a new date is provided
        LocalDate joiningDate = existingEmployee.getDateOfJoining();
        if (dateOfJoining != null && !dateOfJoining.isEmpty()) {
            joiningDate = LocalDate.parse(dateOfJoining);
        }

        employeeService.updateEmployee(employeeId, name, address, contactNumber, email, position, department, joiningDate);
        return "redirect:/employees";
    }



    @PostMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }
}
