package uz.pdp.pdperp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.service.CourseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COURSE_CREATE')")
    @PostMapping("/create")
    public String create(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MENTOR')")
    @GetMapping("/get-all")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{courseId}")
    public Course update(
            @PathVariable @NotNull UUID courseId,
            @Valid @RequestBody Course course
    ) {
        return courseService.updateById(courseId, course);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{courseId}")
    public String delete(
            @PathVariable @NotNull UUID courseId
    ) {
        return courseService.deleteById(courseId);
    }
}
