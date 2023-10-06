package uz.pdp.pdperp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdperp.DTOS.request.GroupCreateDto;
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
    public String create(@RequestBody GroupCreateDto dto) {
        return groupService.create(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all/{mentorId}")
    public List<Group> getAll(@PathVariable UUID mentorId) {
        return groupService.getAll(mentorId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{groupId}")
    public Group update(
            @PathVariable UUID groupId,
            @RequestBody GroupCreateDto dto
    ) {
       return groupService.update(groupId, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{groupId}")
    public String delete(@PathVariable UUID groupId) {
        return groupService.delete(groupId);
    }
}
