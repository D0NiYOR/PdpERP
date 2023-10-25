package uz.pdp.pdperp.DTOS.responce;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO<T> {

    private String message;
    private T object;

}