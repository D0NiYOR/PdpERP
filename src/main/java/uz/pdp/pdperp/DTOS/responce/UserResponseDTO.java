package uz.pdp.pdperp.DTOS.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.pdperp.entity.enums.Permission;
import uz.pdp.pdperp.entity.enums.UserRole;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String email;
    private UserRole role;
    private Set<Permission> permissions;
}
