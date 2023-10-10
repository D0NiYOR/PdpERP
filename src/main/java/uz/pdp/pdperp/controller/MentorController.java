package uz.pdp.pdperp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.MentorCreateDto;
import uz.pdp.pdperp.service.MentorService;

import java.util.UUID;

@RestController
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{ownerId}")
    public String create(
            @PathVariable UUID ownerId,
            @Valid @RequestBody MentorCreateDto dto
    ) {
        return mentorService.create(dto, ownerId);
    }

}
