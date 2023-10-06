package uz.pdp.pdperp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.entity.enums.Permission;
import uz.pdp.pdperp.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/get-all")
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/update-role/{id}")
    public UserEntity updateRole(
            @PathVariable UUID id,
            @RequestParam String role
            ) {
        return userService.updateRole(id, role);
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}/update-permission")
    public UserEntity updatePermission(
            @PathVariable UUID id,
            @RequestParam Set<Permission> permissions
    ) {
        return userService.updatePermission(id, permissions);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable UUID userId) {
        return userService.delete(userId);
    }
}
