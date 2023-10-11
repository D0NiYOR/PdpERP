package uz.pdp.pdperp.service;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.repository.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ModelMapper modelMapper;

    private CourseService courseService;
    private Course course;
    private UUID courseId;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseService(courseRepository, modelMapper);
        course = Course.builder()
                .name("Java")
                .moduleCount(1)
                .startDate(LocalDateTime.parse("2023-10-11T14:35:22.240470500"))
                .endDate(LocalDateTime.parse("2024-08-11T14:35:22.240470500"))
                .build();
        courseId = UUID.randomUUID();

    }

    @Test
    void create() {
        when(courseRepository.save(course)).thenReturn(course);
        String rez = courseService.create(course);
        assertEquals(rez, "create");
    }

    @Test
    void getAll() {
        List<Course> courses = List.of(course);
        when(courseRepository.findAll()).thenReturn(courses);
        List<Course> rez = courseService.getAll();

        assertEquals(rez, courses);
    }

    @Test
    void updateById() {
        Course update = Course.builder().name("C#").build();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(modelMapper.map(update, Course.class)).thenReturn(course);

        Course rez = courseService.updateById(courseId, update);

        assertEquals(rez, course);
    }

    @Test
    void deleteById() {
        when(courseRepository.existsById(courseId)).thenReturn(true);
        String rez = courseService.deleteById(courseId);

        assertEquals(rez, "delete");
    }
}