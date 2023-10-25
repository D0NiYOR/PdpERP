package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.GroupCreateDTD;
import uz.pdp.pdperp.DTOS.request.UpdateGroupDto;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.entity.Lesson;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.CourseRepository;
import uz.pdp.pdperp.repository.GroupRepository;
import uz.pdp.pdperp.repository.LessonRepository;
import uz.pdp.pdperp.repository.MentorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import static uz.pdp.pdperp.entity.enums.LessonStatus.CREATED;
import static uz.pdp.pdperp.entity.enums.LessonStatus.STARTED;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final MentorRepository mentorRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;

    public String create(GroupCreateDTD dto) {
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
        if (mentorId == null) {
            return groupRepository.findAll();
        }
        return groupRepository.findAllByMentorEntity_Id(mentorId);
    }

    public Group update(UUID groupId, UpdateGroupDto dto) {
        Group group = findById(groupId);
        if(dto.getStatus().equals(STARTED)) {
            for (int i = 1; i <= 12; i++) {
                Lesson lesson = Lesson.builder()
                        .group(group)
                        .lessonNumber(i)
                        .module(1)
                        .status(CREATED)
                        .build();
                lessonRepository.save(lesson);
            }
        }
        modelMapper.map(dto, group);
        return groupRepository.save(group);
    }

    public String delete(UUID groupId) {

        if (!groupRepository.existsById(groupId)) {
            throw new DataNotFoundException("group not found");
        }

        lessonRepository.deleteAll(lessonRepository.findLessonsByGroup_Id(groupId));
        groupRepository.deleteById(groupId);
        return "delete✅";
    }


    private Group findById(UUID id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("group not found"));
    }

}
