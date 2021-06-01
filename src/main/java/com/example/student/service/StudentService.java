package com.example.student.service;

import com.example.student.domain.Student;
import com.example.student.util.PageBean;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageBean<Student> queryPage (Map<String,Object> map);
}
