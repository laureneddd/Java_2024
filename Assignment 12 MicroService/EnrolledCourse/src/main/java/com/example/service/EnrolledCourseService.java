package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.repository.CourseRepository;

@Service
public class EnrolledCourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<String> getCourseIdsByUserId(String userId) {
        List<Course> courses = courseRepository.findByUserId(userId);
        return courses.stream().map(Course::getCourseId).collect(Collectors.toList());
    }

    // public List<Course> getAllCourses() {
    //     return courseRepository.findAll();
    // }
}

