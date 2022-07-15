package com.practice.rest;

import com.practice.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController
{

    private List<Student> students;

    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();
        students.add(new Student("Suresh", "Kumar"));
        students.add(new Student("Ramesh", "Patel"));
        students.add(new Student("Mahesh", "Rao"));
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getSpecificStudent(@PathVariable int id)
    {
        //check out-of-bounds exception
        if (id>=students.size())
        {
            throw new StudentNotFoundException("Student is very large : "+id);
        }
        if (id<0)
        {
            throw new StudentNotFoundException("Student id cannot be negative : "+id);
        }
        return students.get(id);
    }

}
