package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

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
        modelMapper.map(update, course);
        courseRepository.save(course);
        return course;
    }

    public String deleteById(UUID courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new DataNotFoundException("Course not found");
        }

        courseRepository.deleteById(courseId);
        return "delete";
    }
}
