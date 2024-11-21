package com.magadhUniversity.controller;

import com.magadhUniversity.model.Subject;
import com.magadhUniversity.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/create")
    public String showCreateSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "create_subject";
    }

    @PostMapping("/create")
    public String createSubject(@ModelAttribute Subject subject, Model model) {
        subjectService.createSubject(subject);
        model.addAttribute("message", "Subject created successfully.");
        return "redirect:/subjects";
    }

    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "list_subjects";
    }

    @GetMapping("/update/{subjectId}")
    public String showUpdateSubjectForm(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.getAllSubjects().stream()
                .filter(sub -> sub.getSubjectId().equals(subjectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject Id:" + subjectId));
        model.addAttribute("subject", subject);
        return "update_subject";
    }

    @PostMapping("/update/{subjectId}")
    public String updateSubject(@PathVariable Long subjectId, @ModelAttribute Subject subject, Model model) {
        subjectService.updateSubject(subjectId, subject);
        model.addAttribute("message", "Subject updated successfully.");
        return "redirect:/subjects";
    }

    @PostMapping("/delete/{subjectId}")
    public String deleteSubject(@PathVariable Long subjectId, Model model) {
        subjectService.deleteSubject(subjectId);
        model.addAttribute("message", "Subject deleted successfully.");
        return "redirect:/subjects";
    }
}
