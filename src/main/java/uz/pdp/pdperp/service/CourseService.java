package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public String create(Course course) {
        courseRepository.save(course);
        return "create";
    }
}
