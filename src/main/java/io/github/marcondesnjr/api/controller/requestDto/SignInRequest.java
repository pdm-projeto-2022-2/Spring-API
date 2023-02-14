package io.github.marcondesnjr.api.controller.requestDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class SignInRequest {

    private String email;
    private String password;
    private String name;
    private String bloodType;
    private String phone;
    private String sex;
    private LocalDate birthDate;
    private MultipartFile image;
    private String location;

}
