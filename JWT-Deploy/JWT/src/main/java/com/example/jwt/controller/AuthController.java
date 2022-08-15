package com.example.jwt.controller;

import com.example.jwt.dto.request.SignUpForm;
import com.example.jwt.dto.response.ResponseMessage;
import com.example.jwt.model.Role;
import com.example.jwt.model.RoleName;
import com.example.jwt.model.User;
import com.example.jwt.service.Impl.RoleServiceImpl;
import com.example.jwt.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public ResponseEntity<?> register (@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again! "), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("The email existed! Please try again! "), HttpStatus.OK);
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role){
                case "admin" : Role adminrole = roleService.findByRole(RoleName.ADMIN).orElseThrow(
                        ()-> new RuntimeException("Role not found")
                );
                roles.add(adminrole);
                break;
                case "pm" : Role pmrole = roleService.findByRole(RoleName.PM).orElseThrow(
                        ()-> new RuntimeException("Role not found")
                );
                roles.add(pmrole);
                break;
                default:
                    Role userRole = roleService.findByRole(RoleName.USER).orElseThrow(
                            ()-> new RuntimeException("Role not found"));
                            roles.add(userRole);
            }
        });
                    user.setRoles(roles);
                    userService.save(user);
                    return new ResponseEntity<>(new ResponseMessage("Create user success!"),HttpStatus.OK);
    }
}
