package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Role;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<StandardResponse> createNewRole(Role role) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.save(role));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create role");
        }
    }

    public ResponseEntity<StandardResponse> getAllRoles() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }
}
