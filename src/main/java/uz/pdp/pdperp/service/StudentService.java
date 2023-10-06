package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.StudentCreateDto;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.entity.StudentEntity;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.GroupRepository;
import uz.pdp.pdperp.repository.MentorRepository;
import uz.pdp.pdperp.repository.StudentRepository;
import uz.pdp.pdperp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    public String create(StudentCreateDto dto) {
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new DataNotFoundException("User not found"));
        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(
                () -> new DataNotFoundException("group not found"));
        StudentEntity student = modelMapper.map(dto, StudentEntity.class);
        student.setActive(dto.isActive());
        student.setGroup(group);
        student.setUserEntity(user);
        studentRepository.save(student);
        return "create";
    }
}
