package io.github.marcondesnjr.api.service.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String password;

}
