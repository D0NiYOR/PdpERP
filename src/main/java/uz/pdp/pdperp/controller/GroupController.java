package uz.pdp.pdperp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.GroupCreateDTD;
import uz.pdp.pdperp.DTOS.request.UpdateGroupDto;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.service.GroupService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@Valid @RequestBody GroupCreateDTD dto) {
        System.out.println();
        return groupService.create(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MENTOR')")
    @GetMapping("/get-all/{mentorId}")
    public List<Group> getAll(@PathVariable @NotNull UUID mentorId) {
        return groupService.getAll(mentorId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{groupId}")
    public Group update(
            @PathVariable @NotNull UUID groupId,
            @Valid @RequestBody UpdateGroupDto dto
    ) {
        System.out.println();
        return groupService.update(groupId, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{groupId}")
    public String delete(@PathVariable @NotNull UUID groupId) {
        return groupService.delete(groupId);
    }
}
