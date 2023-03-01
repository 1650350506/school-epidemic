package com.hmdp.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LoginFormDTO {
    private String phone;
    private String code;

}
