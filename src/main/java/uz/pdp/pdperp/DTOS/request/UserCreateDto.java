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
public class
UserCreateDto {
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotBlank(message = "password must not be blank")
    private String password;
    @NotBlank(message = "user role must not be blank")
    private String role;
    private Set<String> permissions;

}
