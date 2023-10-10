package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCreateDto {

    @NotBlank(message = "user id must not be blank")
    private UUID userId;
    @NotBlank(message = "group id must not be blank")
    private UUID groupId;


}
