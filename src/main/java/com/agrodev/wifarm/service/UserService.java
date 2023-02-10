package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.repository.FarmRepository;
import com.agrodev.wifarm.repository.RoleRepository;
import com.agrodev.wifarm.repository.UserRepository;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.Role;
import com.agrodev.wifarm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(passwordEncoder.encode("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);

    }

    public ResponseEntity<StandardResponse> registerNewUser(User user) {
        try {
            Role role = roleRepo.findByRoleName("User").get();
            Set<Role> roles = new HashSet<>();
            roles.add(role);

            Random customerRand = new Random();
            boolean loggedUser = userRepo.findByUserName(user.getUserName()).isPresent();
            if(!loggedUser) {
                user.setUserId(String.format("%04d", customerRand.nextInt(10000)));
                user.setRole(roles);
                user.setTag("User");
                user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

                Farm farm = new Farm();
                farm.setCustomerId(user.getUserId());
                farm.setPrincipalAmount(0.00);
                farm.setAccuredAmount(0.00);
                farm.setDailyInterestRate(0.00);
                farm.setMonthlyInterestRate(0.00);
                farm.setPlanted(false);

                farmRepository.save(farm);
                User savedUser = userRepo.save(user);
                return StandardResponse.sendHttpResponse(true, "Operation successful!", savedUser, "200");
            }else{
                return StandardResponse.sendHttpResponse(false, "User already exists");
            }
        } catch (Exception e) {

            return StandardResponse.sendHttpResponse(false, "Could not save user");
        }
    }
}
