package com.example.student_library_management_system.service;

import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){
        // convert the request dto into model class
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setGender(studentRequestDto.getGender());
        student.setSem(studentRequestDto.getSem());
        student.setDept(studentRequestDto.getDept());
        student.setAddress(studentRequestDto.getAddress());
        student.setDob(studentRequestDto.getDob());
        student.setMobile(studentRequestDto.getMobile());
        student.setEmail(studentRequestDto.getEmail());

        // whenever student adds, card also gets added as part of cascading
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setExpiryDate(LocalDate.now().plusYears(3).toString());
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);
        return "Student saved successfully!";

    }

    public List<Student> findAllStudents(){
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }

     /*
    Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want to see(0,1,2,3,4,5...)
    pagesize - total number of records in each page(fixed for each page)

    total number of record - 28, page size - 5
    0th page - 1-5
    1st page - 6-10
    2nd page - 11-15
    3rd page - 16-20
    4th page - 21-25
    5th page - 26-28

    total numbers of records-11, page size-3
    0th page - 1-3
    1st page - 4-6
    2nd page - 7-9
    3rd page - 10-11

    // only pagination
    public List<Student> getStudentByPage(int pageNo, int pageSize){
        List<Student> studentList= studentRepository.findAll(PageRequest.of(pageNo,pageSize)).getContent();
        return studentList;
    }

      */

    // pagination along with sorting
    public List<Student> getStudentByPage(int pageNo, int pageSize){
        List<Student> studentList= studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name"))).getContent();
        return studentList;
    }

    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
            throw new RuntimeException("Student not found with id : "+id);
        }
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id : "+id+" deleted successfully!";
    }

    public String updateStudentUsingPut(int studentId, StudentRequestDto newStudentRequestDto){
        Student existingStudent = findStudentById(studentId);
        if (existingStudent!=null){
            existingStudent.setName(newStudentRequestDto.getName());
            existingStudent.setEmail(newStudentRequestDto.getEmail());
            existingStudent.setMobile(newStudentRequestDto.getMobile());
            existingStudent.setDob(newStudentRequestDto.getDob());
            existingStudent.setAddress(newStudentRequestDto.getAddress());
            existingStudent.setSem(newStudentRequestDto.getSem());
            existingStudent.setGender(newStudentRequestDto.getGender());
            existingStudent.setDept(newStudentRequestDto.getDept());

            studentRepository.save(existingStudent);
            return "Student updated successfully!";
        }else{
            return "Student with id : "+studentId+" not found! hence cannot update";
        }

    }

    public Student getStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return  student;
    }

    public List<Student> getStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        return studentList;
    }
}
