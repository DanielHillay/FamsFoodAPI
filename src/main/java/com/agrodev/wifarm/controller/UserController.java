package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping("/getallusers")
    public ResponseEntity<StandardResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUser")
    public ResponseEntity<StandardResponse> getUser(@RequestParam("id") Long id){
        return userService.getUser(id);
    }

    @PutMapping("/updateuserinfo")
    public ResponseEntity<StandardResponse> updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @DeleteMapping("/deleteallusers")
    public ResponseEntity<StandardResponse> deleteAllUsers(){
        return userService.deleteAllUsers();
    }
    @DeleteMapping("/deleteuser")
    public ResponseEntity<StandardResponse> deleteUser(@RequestParam("id") Long id){
        return userService.deleteUser(id);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
