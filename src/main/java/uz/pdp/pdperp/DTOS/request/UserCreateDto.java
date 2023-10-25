package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class
UserCreateDto {
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "username must not be blank")
    private String email;
    @NotBlank(message = "password must not be blank")
    private String password;

}
