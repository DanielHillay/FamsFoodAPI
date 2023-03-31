package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Pojo.LoginRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.service.AuthService;
import com.agrodev.wifarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping({"/login"})
    public ResponseEntity<StandardResponse> createJwtToken(@RequestBody LoginRequest login) throws Exception {
        return StandardResponse.sendHttpResponse(true, "Successful Operation",
                jwtService.createJwtToken(login), "200");
    }

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<StandardResponse> registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/resendOTP")
    public ResponseEntity<StandardResponse> resendOTP(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        return userService.resendOtp(email);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<StandardResponse> forgetPassword(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        return userService.forgetpassword(email);
    }

    @PostMapping("/verifycode")
    public ResponseEntity<StandardResponse> verifyCode(@RequestParam("verificationOtp") String verificationOtp, @RequestParam("email") String email){
        return userService.verifyCode(verificationOtp, email);
    }
}
