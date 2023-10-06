package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.GroupCreateDto;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.CourseRepository;
import uz.pdp.pdperp.repository.GroupRepository;
import uz.pdp.pdperp.repository.MentorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final MentorRepository mentorRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    public String create(GroupCreateDto dto) {
        MentorEntity mentor = mentorRepository.findById(dto.getMentorId()).orElseThrow(
                () -> new DataNotFoundException("mentor not fount"));
        Course course = courseRepository.findById(dto.getCursId()).orElseThrow(
                () -> new DataNotFoundException("course not found"));
        Group group = modelMapper.map(dto, Group.class);
        group.setCourse(course);
        group.setMentorEntity(mentor);
        groupRepository.save(group);
        return "create";
    }

    public List<Group> getAll(UUID mentorId) {
        if(mentorId == null) {
            return groupRepository.findAll();
        }
        return groupRepository.findAllByMentorEntity_Id(mentorId);
    }

    public Group update(UUID groupId, GroupCreateDto dto) {

        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new DataNotFoundException("group not found"));
        if(dto.getMentorId() != null) {
            MentorEntity mentor = mentorRepository.findById(dto.getMentorId())
                    .orElseThrow(() -> new DataNotFoundException("mentor not fount"));
             group.setMentorEntity(mentor);
        }
        if ((dto.getGroupName()) != null && !(dto.getGroupName().equals(""))) {
            group.setGroupName(dto.getGroupName());
        }
        if (dto.getStatus() != null) {
            group.setStatus(dto.getStatus());
        }
        if(dto.getStudentCount() != null) {
            group.setStudentCount(dto.getStudentCount());
        }
        return groupRepository.save(group);
    }

    public String delete(UUID groupId) {
        groupRepository.findById(groupId).orElseThrow(
                () -> new DataNotFoundException("group not found"));
        groupRepository.deleteById(groupId);
        return "delete✅";
    }
}
