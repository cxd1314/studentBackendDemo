package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import com.example.student.util.AjaxResult;
import com.example.student.util.PageBean;
import com.example.student.util.SnGenerateUtil;
import com.example.student.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/studentList")
    public ModelAndView studentList() {
        ModelAndView model = new ModelAndView();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name", "name1");
        m.put("age", "1");list.add(m);
        m = new HashMap<String, Object>();
        m.put("name", "name2");
        m.put("age", "2");
        list.add(m);
        m = new HashMap<String, Object>();
        m.put("name", "name3");
        m.put("age", "3");list.add(m);
        m = new HashMap<String, Object>();
        m.put("name", "name4");
        m.put("age", "4");list.add(m);
        m = new HashMap<String, Object>();
        m.put("name", "name5");
        m.put("age", "5");
        list.add(m);
        model.addObject("list",list);
        model.setViewName("student");

        return  model;
    }

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

    @RequestMapping("/addStudent")
    public AjaxResult addStudent(@RequestParam("file") MultipartFile[] files,Student student) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        student.setSn(SnGenerateUtil.generateSn(student.getClazzId()));

        File fileDir = UploadUtil.getImgDirFile();
        for(MultipartFile fileImg : files) {

            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + uuidName + extName);
                fileImg.transferTo(newFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            student.setPhoto(uuidName + extName);

        }
        try {
            int count = studentService.addStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            }
            else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();;
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }

        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    @RequestMapping("deleteStudent")
    public boolean deleteStudent(@RequestParam String id) {
//        studentService.deleteStudent(id);
        return  true;
    }

}
