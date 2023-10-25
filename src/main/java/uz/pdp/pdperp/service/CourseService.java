package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.CourseCreateDTO;
import uz.pdp.pdperp.DTOS.responce.CourseResponseDTO;
import uz.pdp.pdperp.DTOS.responce.UserResponseDTO;
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

    public CourseResponseDTO create(CourseCreateDTO createDTO) {
        Course course = modelMapper.map(createDTO, Course.class);
        return modelMapper.map(courseRepository.save(course), CourseResponseDTO.class);
    }


    public List<CourseResponseDTO> getAll(Integer page, Integer size) {
        return modelMapper.map(courseRepository.getAll(PageRequest.of(page, size)).getContent(),
                new TypeToken<List<CourseResponseDTO>>() {}.getType());
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
