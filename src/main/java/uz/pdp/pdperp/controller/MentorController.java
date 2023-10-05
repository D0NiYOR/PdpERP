package uz.pdp.pdperp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.MentorCreateDto;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.service.MentorService;

import java.util.UUID;

@RestController
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('MENTOR_CREATE')")
    @PostMapping("/create/{ownerId}")
    public String create(
            @PathVariable UUID ownerId,
            @RequestBody MentorCreateDto dto
    ) {
        System.out.println();
        return mentorService.create(dto, ownerId);
    }

}
