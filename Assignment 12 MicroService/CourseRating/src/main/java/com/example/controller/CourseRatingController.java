package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CourseRatingService;

@RestController
public class CourseRatingController {

    @Autowired
    private CourseRatingService courseRatingService;

    @GetMapping("/getRating/{courseId}")
    public Double getRating(@PathVariable String courseId) {
        return courseRatingService.getRating(courseId);
    }

    @GetMapping("/getComments/{courseId}")
    public List<String> getComments(@PathVariable String courseId) {
        return courseRatingService.getComments(courseId);
    }
    
}
