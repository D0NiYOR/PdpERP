package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.entity.Lesson;
import uz.pdp.pdperp.entity.enums.LessonStatus;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.LessonRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public void create(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public Lesson updateStatus(UUID lessonId, LessonStatus status) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new DataNotFoundException("lesson not found"));
        lesson.setStatus(status);
        return lessonRepository.save(lesson);
    }


    public List<Lesson> getAll(UUID groupId) {
        return lessonRepository.findLessonsByGroup_Id(groupId);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }
}
