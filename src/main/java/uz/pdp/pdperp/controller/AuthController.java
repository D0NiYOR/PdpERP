package uz.pdp.pdperp.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.ResetPasswordDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.request.VerifyDTO;
import uz.pdp.pdperp.DTOS.responce.AuthResponseDTO;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.DTOS.responce.UserResponseDTO;
import uz.pdp.pdperp.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO<UserResponseDTO>> auth(
            @Valid @RequestBody UserCreateDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.add(dto));
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verify(
            @Valid @RequestBody VerifyDTO verifyDTO
    ) {
        return ResponseEntity.ok(authService.verify(verifyDTO));
    }


    @GetMapping("/new-verification-code")
    public ResponseEntity<String> newVerificationCode(@Email @RequestParam String email) {
        return ResponseEntity.ok(authService.newVerifyCode(email));
    }


    @PostMapping("/sign-in")
    public JwtResponse test(@Valid @RequestBody AuthDto dto) {
        return authService.signIn(dto);
    }


    @PutMapping("/forget-password")
    public ResponseEntity<String> forgotPassword(@Email @RequestParam String email) {
        return ResponseEntity.ok(authService.forgetPassword(email));
    }


    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordDto passwordDto
    ) {
        return ResponseEntity.ok(authService.restPassword(passwordDto));
    }

}
