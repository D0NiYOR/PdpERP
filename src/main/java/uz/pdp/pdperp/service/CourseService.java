package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.CourseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public String create(Course course) {
        courseRepository.save(course);
        return "create";
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course updateById(UUID courseId, Course update) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new DataNotFoundException("Course not found"));
        course.setName(update.getName());
        course.setModuleCount(update.getModuleCount());
        course.setStartDate(update.getStartDate());
        course.setEndDate(update.getEndDate());
        courseRepository.save(course);
        return course;
    }

    public String deleteById(UUID courseId) {
        courseRepository.findById(courseId).orElseThrow(
                () -> new DataNotFoundException("Course not found"));
        courseRepository.deleteById(courseId);
        return "delete";
    }
}
