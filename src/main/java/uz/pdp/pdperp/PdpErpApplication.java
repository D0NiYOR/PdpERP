package uz.pdp.pdperp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class PdpErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdpErpApplication.class, args);
        System.out.println(LocalDateTime.now());
    }

}
