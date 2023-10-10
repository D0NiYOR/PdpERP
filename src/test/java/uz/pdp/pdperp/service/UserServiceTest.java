package uz.pdp.pdperp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.config.jwt.JwtService;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.enums.UserRole;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static uz.pdp.pdperp.entity.enums.Permission.*;

class UserServiceTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private UserService userService;
    private UserEntity user;
    private UUID userId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        user = new UserEntity("Bek", "23", "23", UserRole.ADMIN, Set.of(STUDENT_CREATE, STUDENT_READ));
        user.setId(userId);
        userService = new UserService(modelMapper, userRepository, passwordEncoder, jwtService);
    }

    @Test
    void add() {
        UserCreateDto dto = new UserCreateDto("Bek", "23", "23", "ADMIN", Set.of("STUDENT_CREATE", "STUDENT_READ"));
        when(userRepository.findByUsername(dto.getUsername())).thenReturn(Optional.empty());
        when(modelMapper.map(dto, UserEntity.class)).thenReturn(user);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(user.getPassword());
        String result = userService.add(dto);
        assertEquals("Successfully signed up", result);
    }

    @Test
    void signIn() {
        AuthDto dto = new AuthDto("23", "23");
        when(userRepository.findByUsername(dto.getUsername())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("token");

        JwtResponse jwtResponse = userService.signIn(dto);
        assertEquals("token", jwtResponse.getToken());
    }

    @Test
    void userNotFound() {
        AuthDto authDto = new AuthDto("23", "23");
        when(userRepository.findByUsername(authDto.getUsername())).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> userService.signIn(authDto));

        verify(passwordEncoder, never()).matches(any(), any());
        verify(jwtService, never()).generateToken(any());
    }


    @Test
    void updateRole() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserEntity entity = userService.updateRole(userId, "ADMIN");
        assertEquals(UserRole.ADMIN, entity.getRole());
    }

    @Test
    void updatePermission() {

    }

    @Test
    void delete() {
    }
}