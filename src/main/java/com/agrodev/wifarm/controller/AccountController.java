package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Account;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createaccount")
    public ResponseEntity<StandardResponse> createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }
    @PutMapping("/updateaccount")
    public ResponseEntity<StandardResponse> updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }
    @GetMapping("/getaccount")
    public ResponseEntity<StandardResponse> getAccount(@RequestParam("id") Long id){
        return accountService.getAccount(id);
    }
    @GetMapping("/getallaccounts")
    public ResponseEntity<StandardResponse> getAllAccounts(){
        return accountService.getAllAccounts();
    }
    @DeleteMapping("/deleteaccount")
    public ResponseEntity<StandardResponse> deleteAccount(@RequestParam("id") Long id){
        return accountService.deleteAccount(id);
    }
    @DeleteMapping("/deleteallaccounts")
    public ResponseEntity<StandardResponse> deleteAllAccounts(){
        return accountService.deleteAllAccounts();
    }
}
