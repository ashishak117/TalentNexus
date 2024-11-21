package com.magadhUniversity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendancePageController {

    @GetMapping("/mark_attendance")
    public String showMarkAttendancePage() {
        return "mark_attendance";
    }
}
