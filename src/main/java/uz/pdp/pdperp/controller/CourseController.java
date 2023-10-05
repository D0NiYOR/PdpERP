package uz.pdp.pdperp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.service.CourseService;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COURSE_CREATE')")
    @PostMapping("/create")
    public String create(@RequestBody Course course) {
        return courseService.create(course);
    }
}
