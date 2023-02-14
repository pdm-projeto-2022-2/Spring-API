package io.github.marcondesnjr.api.service.dto;

import lombok.Data;

@Data
public class EmployeeDTO extends UserDTO{

    private String name;
    private String registration;
    private String image;

}
