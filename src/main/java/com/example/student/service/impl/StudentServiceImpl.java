package com.example.student.service.impl;

import com.example.student.domain.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import com.example.student.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public PageBean<Student> queryPage(Map<String,Object> map) {
        PageBean<Student> bean = new PageBean<Student>((Integer) map.get("pageno"),(Integer) map.get("pagesize"));
        bean.getStartIndex();
        map.put("startIndex",bean.getStartIndex());

        List<Student> list = studentMapper.queryList(map);
        bean.setDatas(list);

        Integer count = studentMapper.queryCount(map);
        bean.setTotalsize(count);

        return bean;
    }

}
