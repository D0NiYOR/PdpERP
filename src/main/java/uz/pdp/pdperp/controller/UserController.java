package uz.pdp.pdperp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.responce.UserResponseDTO;
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
    public ResponseEntity<List<UserResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(userService.getAll(page, size));
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/update-role/{id}")
    public ResponseEntity<UserResponseDTO> updateRole(
            @PathVariable UUID id,
            @RequestParam String role
    ) {
        return ResponseEntity.ok(userService.updateRole(id, role));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/add-permission/{userId}")
    public ResponseEntity<UserResponseDTO> addPermission(
            @PathVariable UUID userId,
            @RequestParam String permission
    ) {
        return ResponseEntity.ok(userService.addPermission(userId, permission));
    }



    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}/update-permission")
    public ResponseEntity<UserResponseDTO> updatePermission(
            @PathVariable UUID id,
            @RequestParam Set<Permission> permissions
    ) {
        return ResponseEntity.ok(userService.updatePermission(id, permissions));
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }
}
