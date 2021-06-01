package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import com.example.student.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/getStudentList")
    @ResponseBody
    public PageBean<Student> getStudent(@RequestParam Integer page ,@RequestParam Integer rows, String studentName) {
        Map<String,Object> map = new HashMap<>();
        if(studentName!=null && !studentName.equals("")) {
            map.put("username",studentName);
        }
        map.put("pageno",page);
        map.put("pagesize",rows);

        PageBean<Student> datas  = studentService.queryPage(map);
        return datas;
//        return list;
    }
}
