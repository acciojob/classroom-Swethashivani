package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentDb = new HashMap<>();
    HashMap<String, Teacher> teacherDb = new HashMap<>();
    HashMap<Teacher, List<Student>> studentTeacherDb = new HashMap<>();

    public void addStudentInDb(Student student) {
        String name = student.getName();
        studentDb.put(name, student);
    }

    public void addTeacherInDb(Teacher teacher) {
        String name = teacher.getName();
        teacherDb.put(name, teacher);

    }

    public void addStudentTeacherPairInDb(String studentName, String teacherName) {
        Student student = getStudentByNameFromDb(studentName);
        if (studentTeacherDb.containsKey(getTeacherByNameFromDb(teacherName))) {
            List<Student> studentList = studentTeacherDb.get(getTeacherByNameFromDb(teacherName));
            studentList.add(student);
            studentTeacherDb.put(getTeacherByNameFromDb(teacherName), studentList);
        } else {
            List<Student> studentList = new ArrayList<>();
            studentList.add(student);
            studentTeacherDb.put(getTeacherByNameFromDb(teacherName), studentList);
        }

    }


    public Student getStudentByNameFromDb(String name) {
        return studentDb.getOrDefault(name, null);
    }


    public Teacher getTeacherByNameFromDb(String name) {
        return teacherDb.getOrDefault(name, null);
    }

    public List<String> getStudentsByTeacherNameFromDb(String teacherName) {
        List<String> studentNameList = new ArrayList<>();
        Teacher teacher = teacherDb.get(teacherName);
        List<Student> studentList = studentTeacherDb.get(teacher);
        for (Student student : studentList) {
            studentNameList.add(student.getName());
        }
        return studentNameList;
    }

    public List<String> getAllStudentsFromDb() {
        List<String> allStudentList = new ArrayList<>();
        for (Map.Entry<String, Student> set : studentDb.entrySet()) {
            allStudentList.add(set.getKey());
        }
        return allStudentList;
    }

    public void deleteTeacherByNameFromDb(String teacherName) {
        Teacher teacher = teacherDb.get(teacherName);
        List<Student> studentList = studentTeacherDb.get(teacher);
        for (Student student : studentList) {
            studentDb.remove(student.getName());
        }
        teacherDb.remove(teacherName);
        studentTeacherDb.remove(teacher);
    }

    public void deleteAllTeachersFromDb() {
        List<Teacher> teachers = new ArrayList<>();
        for (Map.Entry<Teacher, List<Student>> set :
                studentTeacherDb.entrySet()) {
            List<Student> studentList = set.getValue();
            for (Student student : studentList) {
                studentDb.remove(student.getName());
            }

            teachers.add(set.getKey());
            teacherDb.remove(set.getKey().getName());
        }
        for (Teacher teacher : teachers) {
            studentTeacherDb.remove(teacher);
        }
    }
}

