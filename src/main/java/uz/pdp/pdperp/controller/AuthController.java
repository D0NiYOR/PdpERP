package uz.pdp.pdperp.controller;

import com.example.springsecurity.DTOS.AuthDto;
import com.example.springsecurity.DTOS.UserCreateDto;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String auth (@RequestBody UserCreateDto dto) {
        return userService.add(dto);
    }


    @PostMapping("/sign-in")
    public String test (@RequestBody AuthDto dto) {
        userService.signIn(dto);
        return "test";
    }
}
