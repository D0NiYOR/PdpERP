package uz.pdp.pdperp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationEntity {

    private String verificationCode;
    private LocalDateTime verificationDate;
}
