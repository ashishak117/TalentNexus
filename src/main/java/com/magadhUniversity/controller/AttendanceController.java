package com.magadhUniversity.controller;

import com.magadhUniversity.model.Attendance;
import com.magadhUniversity.model.Employee;
import com.magadhUniversity.model.Student;
import com.magadhUniversity.service.AttendanceService;
import com.magadhUniversity.service.EmployeeService;
import com.magadhUniversity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/record")
    public String recordAttendanceForm(@RequestParam String department, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Student> studentPage = studentService.getStudentsByDepartment(department, PageRequest.of(page, 10));
        List<Student> students = studentPage.getContent();
        Map<Long, Attendance> attendanceMap = new HashMap<>();

        for (Student student : students) {
            List<Attendance> attendances = attendanceService.getAttendanceByStudentId(student.getStudentId());
            for (Attendance attendance : attendances) {
                if (attendance.getDate().equals(LocalDate.now())) {
                    attendanceMap.put(student.getStudentId(), attendance);
                }
            }
        }

        model.addAttribute("students", students);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("department", department);
        model.addAttribute("attendanceMap", attendanceMap);
        return "record_attendance";
    }

    @PostMapping("/batch")
    public String recordBatchAttendance(@RequestParam String department, @RequestParam Map<String, String> params) {
        logger.info("Received attendance batch submission for department: {}", department);

        Page<Student> studentPage = studentService.getStudentsByDepartment(department, PageRequest.of(0, Integer.MAX_VALUE));
        List<Student> students = studentPage.getContent();
        Long lecturerId = 1L; // Replace with actual logic to obtain lecturerId from session or context
        Employee lecturer = employeeService.getEmployeeById(lecturerId); // Fetch lecturer using service

        for (Student student : students) {
            String status = params.get("status" + student.getStudentId());
            if (status == null || status.isEmpty()) {
                logger.warn("No status provided for student: {}", student.getStudentId());
            } else {
                logger.info("Recording attendance for student: {} with status: {}", student.getStudentId(), status);

                Attendance attendance = new Attendance();
                attendance.setStudent(student);
                attendance.setLecturer(lecturer);
                attendance.setDepartment(department);
                attendance.setDate(LocalDate.now());
                attendance.setStatus(status);
                attendanceService.recordAttendance(attendance);
            }
        }

        logger.info("Attendance recorded successfully for department: {}", department);
        return "redirect:/attendance/record?department=" + department;
    }
}
