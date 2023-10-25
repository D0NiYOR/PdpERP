package uz.pdp.pdperp.service;


import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.ResetPasswordDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.DTOS.responce.UserResponseDTO;
import uz.pdp.pdperp.entity.VerificationEntity;
import uz.pdp.pdperp.entity.enums.Permission;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.enums.UserRole;
import uz.pdp.pdperp.exception.BadRequestException;
import uz.pdp.pdperp.exception.DataAlreadyExistsException;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.config.jwt.JwtService;
import uz.pdp.pdperp.exception.DuplicateValueException;
import uz.pdp.pdperp.repository.UserRepository;

import java.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<UserResponseDTO> getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        return modelMapper.map(userRepository.getAll(PageRequest.of(page, size, sort)).getContent(),
                new TypeToken<List<UserResponseDTO>>() {}.getType());
    }



    public UserResponseDTO updateRole(UUID id, String role) {
        UserEntity userEntity = findById(id);
        userEntity.setRole(UserRole.valueOf(role));
        return modelMapper.map(userRepository.save(userEntity), UserResponseDTO.class);
    }


    public UserResponseDTO updatePermission(UUID id, Set<Permission> permissions) {
        UserEntity userEntity = findById(id);
        userEntity.setPermissions(permissions);
        return modelMapper.map(userRepository.save(userEntity), UserResponseDTO.class);
    }

    public UserResponseDTO addPermission(UUID userID, String permission) {
        UserEntity user = findById(userID);

        try {
            user.setRole(UserRole.valueOf(permission));
            return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Enum type not valid: " + permission);
        }
    }

    public String delete(UUID userId) {
        UserEntity user = findById(userId);
        user.setDelete(true);
        userRepository.save(user);
        return "user delete";
    }


    public UserEntity findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found"));
    }

}
