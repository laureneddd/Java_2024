package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.EnrolledCourseService;


@RestController
public class EnrolledCourseController {
     
    @Autowired
    EnrolledCourseService enrolledCourseService;

    @GetMapping("/getCourseId/{userId}")
    public  List<String> getCourseId(@PathVariable String userId) {
        return enrolledCourseService.getCourseIdsByUserId(userId);
    }

    // @GetMapping("/allCourses")
    // public List<Course> getAllCourses() {
    //     return courseCatalogDAO.allCourses();
    // }
    
}
