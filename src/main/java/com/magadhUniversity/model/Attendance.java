package com.magadhUniversity.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id") // Ensures the column name matches in the Student entity
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "employee_id") // Ensures the column name matches in the Employee entity
    private Employee lecturer;

    private String department;
    private LocalDate date;
    private String status;  // Present or Absent

    // Getters and setters
    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getLecturer() {
        return lecturer;
    }

    public void setLecturer(Employee lecturer) {
        this.lecturer = lecturer;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
