package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.repository.FarmRepository;
import com.agrodev.wifarm.repository.RoleRepository;
import com.agrodev.wifarm.repository.UserRepository;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.Role;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.entity.Pojo.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Optional;
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
    @Autowired
    private NotifyService NService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

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

    public void generateOneTimePassword(String email, User user) throws UnsupportedEncodingException, MessagingException {

        Random random = new Random();

        String OTP = String.format("%04d", random.nextInt(10000));

        BCryptPasswordEncoder otppasswordEncoder = new BCryptPasswordEncoder();
        String encodeOTP = otppasswordEncoder.encode(OTP);

        logger.info(" Email not Null and OTP is " + user.getEmail() + " Is " + OTP + " Encoded Otp " + encodeOTP);
//        userRepository.sendNotificationByEmail(OTP, new java.util.Date(), email);
        NService.sendNotificationOTP(user, OTP);

    }

    public void generateEmailOneTimePassword(String email, User user) throws UnsupportedEncodingException, MessagingException {

        Random random = new Random();
        // String OTP = RandomString.make(4);
        String OTP = String.format("%04d", random.nextInt(10000));
        // In actual Sense, we are to save the encoded one so it is not visibile in the database
//        userRepository.sendNotificationByEmail("Use the following OTP :" + OTP, new java.util.Date(), email);
        NService.sendNotificationOTP(user, OTP);
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

    public ResponseEntity<StandardResponse> resendOtp(String userEmail)
            throws UnsupportedEncodingException, MessagingException {
        User datas = userRepo.findByEmail(userEmail).get();

        StandardResponse sr = new StandardResponse();

        if (!ObjectUtils.isEmpty(datas)) {
//            datas.setEmail(userEmail);
            generateOneTimePassword(userEmail, datas);

            sr.setMessage("Email Successfully Sent");
            sr.setStatus(true);
            sr.setStatuscode("200");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
        } else {
            sr.setMessage("Inavlid Email");
            sr.setStatus(false);
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public ResponseEntity<StandardResponse> forgetpassword(String useremail)
            throws UnsupportedEncodingException, MessagingException {
        User datas = userRepo.findByEmail(useremail).get();

        StandardResponse sr = new StandardResponse();

        if (!ObjectUtils.isEmpty(datas)) {

            // Generate and Send an OTP that would be used for the Password Reset
            generateEmailOneTimePassword(useremail, datas);

            sr.setMessage("Email Successfully Sent");
            sr.setStatus(true);
            sr.setStatuscode("200");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
        } else {
            sr.setMessage("Inavlid Email");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public User fetchIsVerifiedStatus(String verificationOtp, User user) {

        User userDB = userRepo.findByVerificationOtp(verificationOtp).get();
//        User userDB = userReposit.findByOneTimePassword(oneTimePassword).get();
        userDB.setValidated(true);
        return userRepo.save(userDB);

    }

    public ResponseEntity<StandardResponse> verifyCode(String verificationOtp, LoginRequest user) {

        User data = userRepo.findByVerificationOtp(verificationOtp).get();
//        User data = userRepository.findByOneTimePassword(oneTimePassword).get();

        StandardResponse sr = new StandardResponse();
        System.out.println("Hello ... 2");

        try {
            sr.setMessage("Password Reset Succesfull");
            sr.setStatus(true);
            sr.setStatuscode("200");

            User userEmail = userRepo.findByUserName(user.getUserName()).get();

            System.out.println("Hello ... 3");

            if(!ObjectUtils.isEmpty(data)&&(data.getEmail().equalsIgnoreCase(userEmail.getEmail()))) {

                Object verifiedStatus = fetchIsVerifiedStatus(verificationOtp, data);

                System.out.println("Hello ... 4 " + verificationOtp);

                sr.setData(verifiedStatus);
                sr.setMessage("Otp Validated");
                sr.setStatus(true);
                sr.setStatuscode("200");

                return new ResponseEntity<>(sr, HttpStatus.OK);
            } else {
                sr.setMessage("Could Not verify Verification Code");
                sr.setStatus(false);
                return new ResponseEntity<>(sr, HttpStatus.OK);

            }

        } catch (Exception e) {
            sr.setStatus(false);
            return new ResponseEntity<>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public ResponseEntity<StandardResponse> resetpassword(String password, String email, String otp) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodepassword = passwordEncoder.encode(password);

        StandardResponse sr = new StandardResponse();

        try {
            sr.setMessage("Password Reset Successfull");
            sr.setStatus(true);
            sr.setStatuscode("200");

            User datas = new User() ;
            Optional<User> usr = userRepo.findByEmail(email);
            if(usr.isPresent()){
                datas = usr.get() ;
            }
            if (!datas.getEmail().isEmpty()) {
//                if (datas.getOneTimePassword().equals(otp)){
//                    datas.setUserPassword(encodepassword);
//                    datas.setVerificationOtp(otp);
////                    userRepository.updatePassword(encodepassword, email);
////                    userRepository.upddateVerficationCode(true, otp);
//                } else {
//                    sr.setMessage("OTP Incorrect");
//                    sr.setStatus(false);
//                    // userRepository.setOnetimePa (true, otp);
//
//                }

                datas.setUserPassword(encodepassword);
//                datas.setVerificationOtp(otp);

                userRepo.save(datas);
                return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
            } else {
                sr.setMessage("Password Reset Unsuccesfull");
                sr.setStatus(false);
                return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);

            }

        } catch (Exception e) {
            sr.setStatus(false);
            sr.setMessage("A problem occurred during password resit, please try later");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }
}
