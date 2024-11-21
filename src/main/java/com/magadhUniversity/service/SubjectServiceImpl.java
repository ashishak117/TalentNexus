package com.magadhUniversity.service;

import com.magadhUniversity.model.Subject;
import com.magadhUniversity.repository.SubjectRepository;
import com.magadhUniversity.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Long subjectId, Subject subject) {
        Subject existingSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        existingSubject.setSubjectName(subject.getSubjectName());
        existingSubject.setDepartment(subject.getDepartment());
        return subjectRepository.save(existingSubject);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }


    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject); // Save subject to the repository
    }
}
