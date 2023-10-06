package uz.pdp.pdperp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.pdperp.DTOS.request.StudentCreateDto;
import uz.pdp.pdperp.service.StudentService;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @PostMapping("/create")
    public String create(@RequestBody StudentCreateDto dto) {
        return studentService.create(dto);
    }
}
