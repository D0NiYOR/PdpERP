package uz.pdp.pdperp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.CourseCreateDTO;
import uz.pdp.pdperp.DTOS.responce.CourseResponseDTO;
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
    public ResponseEntity<CourseResponseDTO> create(
            @Valid @RequestBody CourseCreateDTO createDTO
    ) {
        return ResponseEntity.ok(courseService.create(createDTO));
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MENTOR')")
    @GetMapping("/get-all")
    public ResponseEntity<List<CourseResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(courseService.getAll(page, size));
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
