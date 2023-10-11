package uz.pdp.pdperp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import uz.pdp.pdperp.DTOS.request.StudentCreateDto;
import uz.pdp.pdperp.entity.*;
import uz.pdp.pdperp.entity.enums.UserRole;
import uz.pdp.pdperp.repository.GroupRepository;
import uz.pdp.pdperp.repository.StudentRepository;
import uz.pdp.pdperp.repository.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static uz.pdp.pdperp.entity.enums.GroupStatus.CREATED;
import static uz.pdp.pdperp.entity.enums.Permission.STUDENT_CREATE;
import static uz.pdp.pdperp.entity.enums.Permission.STUDENT_READ;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private StudentService studentService;
    private UserEntity user;
    private StudentEntity student;
    private Group group;
    private UUID userId;
    private UUID groupId;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        studentService = new StudentService(studentRepository, userRepository, groupRepository, modelMapper);

        user = new UserEntity("Bek", "23", "23", UserRole.ADMIN, Set.of(STUDENT_CREATE, STUDENT_READ));
        userId = UUID.randomUUID();
        user.setId(userId);

        student = new StudentEntity();

    }

    @Test
    void create() {
        group = new Group("G23", 20, new Course(), new MentorEntity(), Collections.emptyList(), CREATED);
        groupId = UUID.randomUUID();
        group.setId(groupId);
        StudentCreateDto dto = new StudentCreateDto(userId, groupId);
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(groupRepository.findById(dto.getGroupId())).thenReturn(Optional.of(group));
        when(modelMapper.map(dto, StudentEntity.class)).thenReturn(student);
        student.setUserEntity(user);
        student.setGroup(group);
        student.setActive(true);
        String rez = studentService.create(dto);

        assertEquals("create", rez);
        assertEquals(user, student.getUserEntity());
        assertEquals(group, student.getGroup());
        assertTrue(student.isActive());

    }

    @Test
    void getAll() {
        List<StudentEntity> studentEntities = Collections.singletonList(student);
        when(studentRepository.findStudentEntitiesByGroup_Id(groupId)).thenReturn(studentEntities);
        List<StudentEntity> result = studentService.getAll(groupId);
        assertEquals(studentEntities, result);
    }
}