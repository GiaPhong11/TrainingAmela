package com.example.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-z]{2,30}$",message = "Ten chua ki tu a-z va A-z")
    private String name;

    @NotEmpty( message = "Khong duoc de trong")
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
}