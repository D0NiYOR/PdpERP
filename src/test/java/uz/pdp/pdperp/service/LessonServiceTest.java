package uz.pdp.pdperp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uz.pdp.pdperp.entity.Lesson;
import uz.pdp.pdperp.entity.enums.LessonStatus;
import uz.pdp.pdperp.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;
    private LessonService lessonService;
    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        lessonService = new LessonService(lessonRepository);
        lesson = Lesson.builder()
                .status(LessonStatus.CREATED)
                .module(1)
                .lessonNumber(12)
                .build();
    }

    @Test
    void updateStatus() {
        UUID lessonId = UUID.randomUUID();


        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));

        lesson.setStatus(LessonStatus.STARTED);
        lessonService.updateStatus(lessonId, LessonStatus.STARTED);

        assertEquals(LessonStatus.STARTED, lesson.getStatus());
    }

    @Test
    void getAll() {
        UUID groupId = UUID.randomUUID();
        List<Lesson> lessons = new ArrayList<>();
        when(lessonRepository.findLessonsByGroup_Id(groupId)).thenReturn(lessons);

        List<Lesson> result = lessonService.getAll(groupId);

        assertEquals(result, lessons);
    }

    @Test
    void findAll() {
        List<Lesson> lessons = List.of(lesson);

        when(lessonRepository.findAll()).thenReturn(lessons);

        List<Lesson> res = lessonService.findAll();

        assertEquals(res, lessons);
    }
}