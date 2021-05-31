package com.example.student.controller;

import com.example.student.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/getStudent")
    @ResponseBody
    public List<Student> getStudent() {
        List<Student> list = new LinkedList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setUsername("111");
        s1.setPassword("000");
        list.add(s1);
        return list;
    }
}
