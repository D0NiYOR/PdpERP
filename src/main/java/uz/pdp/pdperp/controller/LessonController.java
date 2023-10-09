package uz.pdp.pdperp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.entity.Lesson;
import uz.pdp.pdperp.entity.enums.LessonStatus;
import uz.pdp.pdperp.service.LessonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    @PreAuthorize("hasRole('ADMIN') or hasRole('MENTOR')")
    @PutMapping("/update-status/{lessonId}")
    public Lesson updateStatus(
            @PathVariable UUID lessonId,
            @RequestParam LessonStatus status,
            @RequestParam Lis
    ) {
        return lessonService.updateStatus(lessonId, status);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MENTOR')")
    @GetMapping("/get-all/{groupId}")
    public List<Lesson> getAll(@PathVariable UUID groupId) {
        return lessonService.getAll(groupId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find-all")
    public List<Lesson> findAll() {
        return lessonService.findAll();
    }
}
