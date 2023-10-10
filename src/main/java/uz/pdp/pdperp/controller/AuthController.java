package uz.pdp.pdperp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.pdperp.DTOS.request.AuthDto;
import uz.pdp.pdperp.DTOS.request.UserCreateDto;
import uz.pdp.pdperp.DTOS.responce.JwtResponse;
import uz.pdp.pdperp.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String auth(@Valid @RequestBody UserCreateDto dto) {
        return userService.add(dto);
    }


    @PostMapping("/sign-in")
    public JwtResponse test(@Valid @RequestBody AuthDto dto) {
        return userService.signIn(dto);
    }
}
