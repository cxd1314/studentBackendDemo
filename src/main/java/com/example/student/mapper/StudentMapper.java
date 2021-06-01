package com.example.student.mapper;

import com.example.student.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    List<Student> queryList(Map<String,Object> paramMap);

    Integer queryCount(Map<String,Object> paramMap);
}
