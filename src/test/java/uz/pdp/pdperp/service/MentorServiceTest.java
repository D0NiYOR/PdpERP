package uz.pdp.pdperp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import uz.pdp.pdperp.DTOS.request.MentorCreateDto;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.enums.UserRole;
import uz.pdp.pdperp.repository.MentorRepository;
import uz.pdp.pdperp.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static uz.pdp.pdperp.entity.enums.Permission.STUDENT_CREATE;
import static uz.pdp.pdperp.entity.enums.Permission.STUDENT_READ;

class MentorServiceTest {
    @Mock
    private MentorRepository mentorRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private MentorService mentorService;
    private MentorEntity mentor;
    private UserEntity user;
    private UUID userId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mentorService = new MentorService(mentorRepository, userRepository, modelMapper);

        user = new UserEntity("Bek", "23", "23", UserRole.ADMIN, Set.of(STUDENT_CREATE, STUDENT_READ));
        userId = UUID.randomUUID();
        user.setId(userId);

        mentor = new MentorEntity();
    }

    @Test
    void create() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        MentorCreateDto dto = new MentorCreateDto();
        when(modelMapper.map(dto, MentorEntity.class)).thenReturn(mentor);
        mentor.setUserEntity(user);

        String result = mentorService.create(dto, userId);

        assertEquals(result, "Successes");
    }
}