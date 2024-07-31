package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.repository.CourseRepository;

@Service
public class CourseRatingService {

    @Autowired
    private CourseRepository courseRepository;

    public Double getRating(String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.isPresent() ? course.get().getRating() : 0.0;
    }

    public List<String> getComments(String courseId) {

        Optional<Course> course = courseRepository.findById(courseId);
        return course.isPresent() ? course.get().getComments() : new ArrayList<>();
    }

    // public List<Course> getAllCourses() {
    //     return courseRepository.findAll();
    // }
}

