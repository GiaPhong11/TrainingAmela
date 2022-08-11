package com.example.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Validator {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-z]{2,30}$",message = "Ten chua ki tu a-z va A-z")
    private String name;

    private String address;

    @NotEmpty
    @Pattern(regexp = "(?=.{12}$)([0-9]{2,4}\\-[0-9]{2,4}\\-[0-9]{2,4})",message = "Sai dinh dang xxx-xxx-xxxx")
    private String phone ;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$",message = "Sai dinh dang")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$",message = "Sai dinh dang")
    private String password;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String address = user.getAddress();

        if(address == null || "".equals(address)){
            errors.rejectValue("address","error.address.blank");
        }
    }
}