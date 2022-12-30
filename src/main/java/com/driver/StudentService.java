package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.addStudentInDb(student);
    }


    public void addTeacher(Teacher teacher) {
        studentRepository.addTeacherInDb(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        studentRepository.addStudentTeacherPairInDb(student, teacher);
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByNameFromDb(name);
    }

    public Teacher getTeacherByName(String name) {
        return studentRepository.getTeacherByNameFromDb(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentRepository.getStudentsByTeacherNameFromDb(teacher);
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudentsFromDb();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepository.deleteTeacherByNameFromDb(teacher);
    }

    public void deleteAllTeachers() {
        studentRepository.deleteAllTeachersFromDb();
    }
}
