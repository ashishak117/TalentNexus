package com.magadhUniversity.controller;

import com.magadhUniversity.model.Employee;
import com.magadhUniversity.model.Student;
import com.magadhUniversity.model.Subject;
import com.magadhUniversity.service.EmployeeService;
import com.magadhUniversity.service.StudentService;
import com.magadhUniversity.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    // Dashboard Home
    @GetMapping("/home")
    public String adminHome(Model model) {
        model.addAttribute("employeeCount", employeeService.getAllEmployees().size());
        model.addAttribute("studentCount", studentService.getAllStudents().size());
        model.addAttribute("subjectCount", subjectService.getAllSubjects().size());
        return "adminHome";
    }

    // View all employees
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "list_employee";
    }

    // View all students
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list_students";
    }

    // View all subjects
    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "list_subjects";
    }

    // Add new employee
    @GetMapping("/addEmployee")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create_employee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/admin/employees";
    }

    // Add new student
    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/students";
    }

    // Add new subject
    @GetMapping("/addSubject")
    public String addSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "create_subjects";
    }

    @PostMapping("/addSubject")
    public String addSubject(@ModelAttribute Subject subject) {
        subjectService.addSubject(subject);
        return "redirect:/admin/subjects";
    }

    // Manage attendance (for both employees and students)
    @GetMapping("/manageAttendance")
    public String manageAttendance() {
        // Display attendance management page
        return "manage_attendance";
    }

    // Reports
    @GetMapping("/reports")
    public String viewReports() {
        // Display reports page
        return "admin_reports";
    }

    // Other operations can be added similarly...
}
