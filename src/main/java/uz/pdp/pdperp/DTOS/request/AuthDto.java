package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDto {
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotBlank(message = "password must not be blank")
    private String password;
}

