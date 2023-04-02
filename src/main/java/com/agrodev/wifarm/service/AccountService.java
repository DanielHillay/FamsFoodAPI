package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Account;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<StandardResponse> createAccount(Account account) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", accountRepository.save(account));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }

    public ResponseEntity<StandardResponse> updateAccount(Account account) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", accountRepository.save(account));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }

    public ResponseEntity<StandardResponse> getAccount(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", accountRepository.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }

    public ResponseEntity<StandardResponse> getAllAccounts() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", accountRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }

    public ResponseEntity<StandardResponse> deleteAccount(Long id) {
        try {
            accountRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllAccounts() {
        try {
            accountRepository.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create account");
        }
    }
}
