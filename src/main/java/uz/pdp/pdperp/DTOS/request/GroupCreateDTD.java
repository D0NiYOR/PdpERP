package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.pdperp.entity.enums.GroupStatus;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroupCreateDTD {
    @NotBlank(message = "group name must not be blank")
    private String groupName;
    @NotNull(message = "user id must not be blank")
    private UUID cursId;
    @NotBlank(message = "mentor id must not be blank")
    private UUID mentorId;
    @NotBlank(message = "student count must not be blank")
    private Integer studentCount;
    @NotBlank(message = "group status must not be blank")
    private GroupStatus status;

}
