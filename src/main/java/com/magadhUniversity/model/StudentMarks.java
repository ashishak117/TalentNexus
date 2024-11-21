package com.magadhUniversity.model;

import jakarta.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "student_marks")
public class StudentMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, insertable = false, updatable = false)
    private Student student;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "semester")
    private String semester;

    @Column(name = "internal1")
    private Double internal1;

    @Column(name = "internal2")
    private Double internal2;

    @Column(name = "internal3")
    private Double internal3;

    @Column(name = "best_internal_marks")
    private Double bestInternalMarks;

    @Column(name = "final_exam_marks")
    private Double finalExamMarks;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "division")
    private String division;

    // Getters and Setters
    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Double getInternal1() {
        return internal1;
    }

    public void setInternal1(Double internal1) {
        this.internal1 = internal1;
    }

    public Double getInternal2() {
        return internal2;
    }

    public void setInternal2(Double internal2) {
        this.internal2 = internal2;
    }

    public Double getInternal3() {
        return internal3;
    }

    public void setInternal3(Double internal3) {
        this.internal3 = internal3;
    }

    public Double getBestInternalMarks() {
        return bestInternalMarks;
    }

    public void setBestInternalMarks(Double bestInternalMarks) {
        this.bestInternalMarks = bestInternalMarks;
    }

    public Double getFinalExamMarks() {
        return finalExamMarks;
    }

    public void setFinalExamMarks(Double finalExamMarks) {
        this.finalExamMarks = finalExamMarks;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    // Method to calculate the best internal marks for theoretical subjects
    public void calculateBestInternalMarks() {
        Double[] internals = {internal1, internal2, internal3};
        Arrays.sort(internals, (a, b) -> Double.compare(b, a)); // Sort descending
        this.bestInternalMarks = (internals[0] + internals[1]) / 2;
    }

    // Method to calculate total marks and percentage
    public void calculateTotalMarksAndPercentage() {
        this.totalMarks = this.bestInternalMarks + this.finalExamMarks;
        this.percentage = (this.totalMarks / 125) * 100; // Assuming 125 is the total for theoretical subjects
        calculateDivision();
    }

    // Method to calculate division based on percentage
    public void calculateDivision() {
        if (this.percentage < 33) {
            this.division = "FAIL";
        } else if (this.percentage >= 33 && this.percentage < 45) {
            this.division = "3RD";
        } else if (this.percentage >= 45 && this.percentage < 60) {
            this.division = "2ND";
        } else if (this.percentage >= 60) {
            this.division = "1ST";
        }
    }
}
