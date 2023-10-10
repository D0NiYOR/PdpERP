package uz.pdp.pdperp.service;


import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.entity.enums.Permission;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.enums.UserRole;
import uz.pdp.pdperp.exception.DataAlreadyExistsException;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.config.jwt.JwtService;
import uz.pdp.pdperp.repository.UserRepository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String add(UserCreateDto dto) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(dto.getUsername());
        if (userEntity.isPresent()) {
            throw new DataAlreadyExistsException("User already exists");
        }
        UserEntity map = modelMapper.map(dto, UserEntity.class);
        setPermissions(map, dto.getPermissions());
        map.setPassword(passwordEncoder.encode(map.getPassword()));
        userRepository.save(map);
        return "Successfully signed up";
    }

    private UserEntity setPermissions(UserEntity userEntity, Set<String> permissions) {
        Set<Permission> collect = permissions.stream()
                .map(permission -> Permission.valueOf(permission.toUpperCase()))
                .collect(Collectors.toSet());
        userEntity.setPermissions(collect);
        return userEntity;
    }

    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity updateRole(UUID id, String role) {
        UserEntity userEntity = findById(id);
        userEntity.setRole(UserRole.valueOf(role));
        userEntity.setUpdatedDate(LocalDateTime.now());
        return userRepository.save(userEntity);
    }

    public UserEntity updatePermission(UUID id, Set<Permission> permissions) {

        UserEntity userEntity = findById(id);
        userEntity.setPermissions(permissions);
        userEntity.setUpdatedDate(LocalDateTime.now());
        return userRepository.save(userEntity);
    }

    public String delete(UUID userId) {
        findById(userId);
        userRepository.deleteById(userId);
        return "user delete";
    }

    private UserEntity findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("user not found"));
    }
}
