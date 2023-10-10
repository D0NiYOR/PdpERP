package uz.pdp.pdperp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.StudentCreateDto;
import uz.pdp.pdperp.entity.StudentEntity;
import uz.pdp.pdperp.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@Valid  @RequestBody StudentCreateDto dto) {
        return studentService.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MENTOR')")
    @GetMapping("/get-all/{groupId}")
    public List<StudentEntity> getAll(@PathVariable UUID groupId) {
        System.out.println();
        return studentService.getAll(groupId);
    }
}
