package uz.pdp.pdperp.DTOS.request;

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

    private UUID userId;
    private UUID groupId;
    private boolean isActive;

}
