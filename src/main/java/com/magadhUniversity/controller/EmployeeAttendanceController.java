package com.magadhUniversity.controller;

import com.magadhUniversity.model.Employee;
import com.magadhUniversity.model.EmployeeAttendance;
import com.magadhUniversity.service.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employees/attendance")
public class EmployeeAttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAttendanceController.class);

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    @PostMapping("/mark")
    public String markAttendance(@RequestParam("employeeId") Long employeeId,
                                 @RequestParam("date") String date,
                                 @RequestParam("status") String status,
                                 Model model) {
        logger.info("Marking attendance for employee ID: {}", employeeId);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.parse(date));
        attendance.setStatus(status);

        // Check if attendance for the day has already been marked
        List<EmployeeAttendance> existingAttendance = employeeAttendanceService.getAttendanceByEmployeeId(employeeId);
        for (EmployeeAttendance att : existingAttendance) {
            if (att.getDate().equals(attendance.getDate())) {
                model.addAttribute("message", "Attendance for the day has already been marked.");
                return "mark_attendance";
            }
        }
        employeeAttendanceService.markAttendance(attendance);
        model.addAttribute("message", "Attendance marked successfully.");
        return "mark_attendance";
    }

    @GetMapping("/view")
    public String viewAttendance(@RequestParam Long employeeId, Model model) {
        logger.info("Viewing attendance for employee ID: {}", employeeId);

        List<EmployeeAttendance> attendanceRecords = employeeAttendanceService.getAttendanceByEmployeeId(employeeId);
        if (!attendanceRecords.isEmpty()) {
            Employee employee = attendanceRecords.get(0).getEmployee();
            model.addAttribute("employeeName", employee.getName());
            model.addAttribute("employeeId", employee.getEmployeeId());
        }
        model.addAttribute("attendanceRecords", attendanceRecords);
        return "view_attendance";
    }

    // Add this method to handle managing attendance
    @GetMapping("/manage")
    public String manageAttendance(Model model) {
        logger.info("Managing attendance");

        List<EmployeeAttendance> attendanceRecords = employeeAttendanceService.getAllAttendanceRecords();
        model.addAttribute("attendanceRecords", attendanceRecords);
        return "manage_attendance";
    }

//    @PostMapping("/update")
//    public String updateAttendance(@RequestParam("attendanceId") Long attendanceId,
//                                   @RequestParam("employeeId") Long employeeId,
//                                   @RequestParam("date") String date,
//                                   @RequestParam("status") String status,
//                                   Model model) {
//        logger.info("Updating attendance for ID: {}", attendanceId);
//
//        Employee employee = new Employee();
//        employee.setEmployeeId(employeeId);
//
//        EmployeeAttendance attendance = new EmployeeAttendance();
//        attendance.setAttendanceId(attendanceId);
//        attendance.setEmployee(employee);
//        attendance.setDate(LocalDate.parse(date));
//        attendance.setStatus(status);
//
//        employeeAttendanceService.markAttendance(attendance);
//        model.addAttribute("message", "Attendance updated successfully.");
//        return "redirect:/employees/attendance/manage";
//    }
    @PostMapping("/update")
    public String updateAttendance(@RequestParam("attendanceId") Long attendanceId,
                                   @RequestParam("employeeId") Long employeeId,
                                   @RequestParam("date") String date,
                                   @RequestParam("status") String status,
                                   RedirectAttributes redirectAttributes) {
        logger.info("Updating attendance for ID: {}", attendanceId);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setAttendanceId(attendanceId);
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.parse(date));
        attendance.setStatus(status);

        employeeAttendanceService.markAttendance(attendance);
        redirectAttributes.addFlashAttribute("message", "Attendance updated successfully.");
        return "redirect:/employees/attendance/manage";
    }


    @PostMapping("/delete/{attendanceId}")
    public String deleteAttendance(@PathVariable Long attendanceId, Model model) {
        logger.info("Deleting attendance for ID: {}", attendanceId);

        employeeAttendanceService.deleteAttendance(attendanceId);
        model.addAttribute("message", "Attendance deleted successfully.");
        return "redirect:/employees/attendance/manage";
    }

    // [UPDATED] Added method to handle GET requests for the department form
    @GetMapping("/department")
    public String showDepartmentForm(Model model) {
        logger.info("Rendering department form for attendance."); // Logging for debugging
        return "employee_department"; // Refers to "department_form.html" template
    }

    @PostMapping("/department")
    public String handleDepartmentSubmission(@RequestParam("department") String department) {
        logger.info("Received department submission: {}", department);
        return "redirect:/attendance/record?department=" + department;
    }


}
