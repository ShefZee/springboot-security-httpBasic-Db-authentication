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
    @PreAuthorize("hasAnyAuthority('ADMIN_WRITE', 'ADMIN_DELETE')")
    public ResponseEntity getTest1(){
        return ResponseEntity.ok("Test 1, Admin Write, Delete access");
    }

    @GetMapping("/api/test2")
    @PreAuthorize("hasAnyAuthority('ADMIN_WRITE')")
    public ResponseEntity getTest2(){
        return ResponseEntity.ok("Test 2, Admin Write access");
    }

    @GetMapping("/api/test3")
    @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    public ResponseEntity getTest3(){
        return ResponseEntity.ok("Test 3, Admin Delete access");
    }

    @GetMapping("/api/test4")
    @PreAuthorize("hasRole('ROLE_ADMIN_L1')")
    public ResponseEntity getTest4(){
        return ResponseEntity.ok("Test 4, Admin_L1 access");
    }

    @GetMapping("/api/test5")
    @PreAuthorize("hasRole('ROLE_ADMIN_L2')")
    public ResponseEntity getTest5(){
        return ResponseEntity.ok("Test 5, Admin_L2 access");
    }
}
