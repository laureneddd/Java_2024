package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.User;
import com.example.service.CourseCatalogService;



@RestController
public class CourseCatalogController {
     
    @Autowired
    CourseCatalogService courseCatalogService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getCourseId/{userId}")
    public List<String> getCourseId(@PathVariable String userId) {

        Optional<User> optional = courseCatalogService.findUserById(userId);

        List<String> courseIds = new ArrayList<>();

        if(optional.isPresent()) {

            courseIds = restTemplate.getForObject("http://EnrolledCourse/getCourses/"+userId, List.class);

        }
        return courseIds;
    }

    @GetMapping("/getRating/{courseId}")
    public Double getRatingByCourseId(@PathVariable String courseId) {

        Double rating  = restTemplate.getForObject("http://CourseRating/getRating/" + courseId, Double.class);

        return rating;
        
    }

    @GetMapping("/getComments/{courseId}")
    public List<String> getCommentsByCourseId(@PathVariable String courseId) { 
         
        List<String> comments = restTemplate.getForObject("http://CourseRating/getComments/" + courseId, List.class);
         
        return comments;
    }
    
    
    

    // @GetMapping("/getCourse/{userId}")
    // public  List<Course> getCourses(@PathVariable String userId) {
    //     return courseCatalogDAO.getCourses(userId);
    // }

    // @GetMapping("/allCourses")
    // public List<Course> getAllCourses() {
    //     return courseCatalogDAO.allCourses();
    // }
    
}
