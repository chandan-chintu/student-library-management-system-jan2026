package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.saveStudent(studentRequestDto);
        return response;
    }

    @GetMapping("/findById/{id}")
    public Object findStudentById(@PathVariable int id){
        try {
            Student student = studentService.findStudentById(id);
            return student;
        } catch (Exception e){
            return "Exception occurred : "+e.getMessage()+"---"+e.getClass();
        }
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents(){
       List<Student> studentList = studentService.findAllStudents();
       return studentList;
    }

    @GetMapping("/findAllByPage")
    public List<Student> findAllStudentsByPage(@RequestParam int pageNo,@RequestParam int pageSize){
        List<Student> studentList = studentService.getStudentByPage(pageNo, pageSize);
        return studentList;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
        String response = studentService.deleteStudentById(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public String updatedStudent(@PathVariable int id,@RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudentUsingPut(id, studentRequestDto);
        return response;
    }

    @GetMapping("/findByEmail")
    public Student findStudentByEmail(@RequestParam String email){
        Student student = studentService.getStudentByEmail(email);
        return student;
    }

    @GetMapping("/findByDept")
    public List<Student> findStudentByDept(@RequestParam String dept){
        List<Student> studentList = studentService.getStudentByDept(dept);
        return studentList;
    }

}
