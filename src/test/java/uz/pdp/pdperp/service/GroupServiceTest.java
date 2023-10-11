package uz.pdp.pdperp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import uz.pdp.pdperp.DTOS.request.GroupCreateDto;
import uz.pdp.pdperp.DTOS.request.UpdateGroupDto;
import uz.pdp.pdperp.entity.Course;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.entity.enums.GroupStatus;
import uz.pdp.pdperp.repository.CourseRepository;
import uz.pdp.pdperp.repository.GroupRepository;
import uz.pdp.pdperp.repository.LessonRepository;
import uz.pdp.pdperp.repository.MentorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private MentorRepository mentorRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private LessonRepository lessonRepository;
    private GroupService groupService;
    private GroupCreateDto dto;
    private UpdateGroupDto updateGroupDto;
    private MentorEntity mentor;
    private Course course;
    private Group group;
    private UUID groupId;
    private UUID courseId;
    private UUID mentorId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        groupService = new GroupService(groupRepository, mentorRepository, courseRepository, modelMapper, lessonRepository);
        updateGroupDto = new UpdateGroupDto();
        groupId = UUID.randomUUID();
        mentorId = UUID.randomUUID();
        courseId = UUID.randomUUID();
        group = new Group();
        mentor = new MentorEntity();
        course = new Course();
        dto = new GroupCreateDto("Java", courseId, mentorId, 20, GroupStatus.CREATED);
    }

    @Test
    void create() {

        when(mentorRepository.findById(dto.getMentorId())).thenReturn(Optional.of(mentor));
        when(courseRepository.findById(dto.getCursId())).thenReturn(Optional.of(course));
        when(modelMapper.map(dto, Group.class)).thenReturn(group);

        group.setMentorEntity(mentor);
        group.setCourse(course);

        String rez = groupService.create(dto);

        assertEquals("create", rez);
    }

    @Test
    void getAll() {
        List<Group> groups = Collections.singletonList(group);
        when(groupRepository.findAll()).thenReturn(groups);
        List<Group> res = null;
        if (mentorId != null) {
            when(groupRepository.findAllByMentorEntity_Id(mentorId)).thenReturn(groups);
            res = groupService.getAll(mentorId);
        }
        assertEquals(res, groups);
    }

    @Test
    void update() {
        updateGroupDto = UpdateGroupDto.builder().groupName("G26").build();
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        when(modelMapper.map(updateGroupDto, Group.class)).thenReturn(group);
        when(groupRepository.save(group)).thenReturn(group);

        Group rez = groupService.update(groupId, updateGroupDto);
        assertEquals(rez, group);

    }

    @Test
    void delete() {
        when(groupRepository.existsById(groupId)).thenReturn(true);
        String rez = groupService.delete(groupId);
        assertEquals(rez, "delete✅");
    }
}