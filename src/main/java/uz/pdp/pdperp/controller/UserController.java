package uz.pdp.pdperp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.UserCreateDto;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER_CREATE')")
    @PostMapping
    public String create(@RequestBody UserCreateDto userCreateDto) {
        return userService.add(userCreateDto);
    }

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER_READ')")
    @GetMapping("/get-all")
    public List<UserEntity> getAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return userService.getAll(page, size);
    }

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER_UPDATE')")
    @PutMapping("/update")
    public UserEntity update(
            @RequestParam UUID id,
            @RequestParam String role
            ) {
        return userService.update(id, role);
    }

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER_DELETE')")
    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable UUID userId) {
        return userService.delete(userId);
    }
}
