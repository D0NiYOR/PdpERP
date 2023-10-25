package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.ResetPasswordDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.request.VerifyDTO;
import uz.pdp.pdperp.DTOS.responce.AuthResponseDTO;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.DTOS.responce.UserResponseDTO;
import uz.pdp.pdperp.config.jwt.JwtService;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.VerificationEntity;
import uz.pdp.pdperp.exception.BadRequestException;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.exception.DuplicateValueException;
import uz.pdp.pdperp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

import static uz.pdp.pdperp.entity.enums.UserRole.USER;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;
    private final MailSenderService mailSenderService;


    private Map<String, UserEntity> userMap = new HashMap<>();

    public AuthResponseDTO<UserResponseDTO> add(UserCreateDto dto) {
        existsUserByEmail(dto.getEmail());
        UserEntity user = modelMapper.map(dto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerificationEntity(generateVerificationData());
        userMap.put(dto.getEmail(), user);
        String message = mailSenderService.sendVerificationCode(user.getEmail(),
                user.getVerificationEntity().getVerificationCode());
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        return new  AuthResponseDTO<>(message, userResponseDTO);
    }


    public String verify(VerifyDTO verifyDTO) {
        UserEntity user = userMap.get(verifyDTO.getEmail());
        if(checkVerificationCodeAndExpiration(user.getVerificationEntity(), verifyDTO.getVerificationCode())){
            return "Verification code wrong";
        }
        user.setRole(USER);
        userRepository.save(user);
        return "Successfully verified";
    }


    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findUserEntitiesByEmail(dto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }


    public String newVerifyCode(String email) {
        UserEntity user = findUserEntitiesByEmail(email);
        user.setVerificationEntity(generateVerificationData());
        userRepository.save(user);
        return mailSenderService.sendVerificationCode(email, user.getVerificationEntity().getVerificationCode());
    }



    public String forgetPassword(String email) {
        UserEntity user = findUserEntitiesByEmail(email);
        user.setVerificationEntity(generateVerificationData());
        userRepository.save(user);
        return mailSenderService.sendVerificationCode(email, user.getVerificationEntity().getVerificationCode());
    }


    public String restPassword(ResetPasswordDto resetPasswordDto) {
        UserEntity user = findUserEntitiesByEmail(resetPasswordDto.getEmail());
        if(checkVerificationCodeAndExpiration(user.getVerificationEntity(), resetPasswordDto.getVerificationCode())) {
            return "Verification code wrong";
        }
        user.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(user);
        return "Password successfully changed";
    }


    private boolean checkVerificationCodeAndExpiration(VerificationEntity verification, String verificationCode) {
        if(!verification.getVerificationDate().plusMinutes(100).isAfter(LocalDateTime.now()))
            throw new BadRequestException("Verification code expired");
        return !Objects.equals(verification.getVerificationCode(), verificationCode);
    }


    private VerificationEntity generateVerificationData() {
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(100000, 999999));
        return new VerificationEntity(verificationCode, LocalDateTime.now());
    }


    private UserEntity findUserEntitiesByEmail(String email) {
        return userRepository.findUserEntitiesByEmail(email).orElseThrow(
                () -> new DataNotFoundException("user not found"));
    }

    private void existsUserByEmail(String email) {
        if (userRepository.existsUserEntitiesByEmail(email))
            throw new DuplicateValueException("User already exists with Email: " + email);
    }
}
