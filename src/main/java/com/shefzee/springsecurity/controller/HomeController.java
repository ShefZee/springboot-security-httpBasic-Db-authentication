package com.shefzee.springsecurity.controller;

import com.shefzee.springsecurity.repository.RoleRepository;
import com.shefzee.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/api/users")
    public ResponseEntity getAllUsers(){

        return ResponseEntity.ok(userRepository.findAll());

    }

    @GetMapping("/api/roles")
    public ResponseEntity getAllRoles(){

        return ResponseEntity.ok(roleRepository.findAll());

    }

    @GetMapping("/api/test1")
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ', 'DELETE')")
    //shef1 & shef2 has access
    public ResponseEntity getTest1(){
        return ResponseEntity.ok("Test 1, Write, Delete access");
    }

    @GetMapping("/api/test2")
    @PreAuthorize("hasAnyAuthority('READ')")
    //shef1 & shef2 has access
    public ResponseEntity getTest2(){
        return ResponseEntity.ok("Test 2, Write access");
    }

    @GetMapping("/api/test3")
    @PreAuthorize("hasAnyAuthority('DELETE')")
    //shef1 has access
    public ResponseEntity getTest3(){
        return ResponseEntity.ok("Test 3, Delete access");
    }

    @GetMapping("/api/test4")
    @PreAuthorize("hasRole('ROLE_USER_L1')")
    //shef1 has access
    public ResponseEntity getTest4(){
        return ResponseEntity.ok("Test 4, USER_L1 access");
    }

    @GetMapping("/api/test5")
    @PreAuthorize("hasRole('ROLE_USER_L2')")
    //shef2 has access
    public ResponseEntity getTest5(){
        return ResponseEntity.ok("Test 5, USER_L2 access");
    }

    @GetMapping("/api/test6")
    @PreAuthorize("hasRole('ROLE_USER_L3')")
    //shef3 has access
    public ResponseEntity getTest6(){
        return ResponseEntity.ok("Test 6, USER_L3 access");
    }

}
