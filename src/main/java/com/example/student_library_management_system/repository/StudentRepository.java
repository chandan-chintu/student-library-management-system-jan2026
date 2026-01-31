package com.example.student_library_management_system.repository;

import com.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //writing custom user defined queries

    @Query(nativeQuery = true, value = "select * from student where email = :inputEmail")
    public Student getStudentByEmail(String inputEmail);

    @Query(nativeQuery = true, value = "select * from student where dept = :inputDept")
    public List<Student> getStudentByDept(String inputDept);
}
