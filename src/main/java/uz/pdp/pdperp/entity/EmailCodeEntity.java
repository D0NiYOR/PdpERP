package uz.pdp.pdperp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "email_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailCodeEntity extends BaseEntity{

    @Column(unique = true)
    private String email;
    private String code;
    @Timestamp
    private LocalDateTime limits;
}
