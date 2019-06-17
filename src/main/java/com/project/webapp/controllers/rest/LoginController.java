package com.project.webapp.controllers.rest;

import com.project.webapp.dto.request.LoggedInUserDTO;
import com.project.webapp.dto.request.LoginDTO;
import com.project.webapp.dto.response.AppUserDto;
import com.project.webapp.model.AppUser;
import com.project.webapp.security.TokenUtils;
import com.project.webapp.service.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl") //there are more than one bean of the same type and want to wire only one of them with a property
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AppUserService appUserService;    

    @Autowired
    TokenUtils tokenUtils; 
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        try {
        	System.out.println("LoginController username: " + user.getEmail());
        	System.out.println("LoginController password: " + user.getPassword());
        	
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
            AppUser userDb = appUserService.findByEmail(user.getEmail());
            LoggedInUserDTO loggedIn = new LoggedInUserDTO(userDb.getIdAppUser(), tokenUtils.generateToken(details),details.getUsername(), userDb.getEmail(), details.getAuthorities());
            if (!userDb.isComfirmed()) {
                throw new Exception("You must verify email in order to login!");
            }
            if(!userDb.isPasswordChanged()){
                throw new Exception("You need to change temporary password to login!");
            }
            if(userDb.isDeleted()){
                throw new Exception("User account is disabled!");
            }
            return new ResponseEntity<>(loggedIn, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value = "/signup")
    public ResponseEntity<LoginDTO> signUp(@RequestBody LoginDTO user) {
    	System.out.println("LoginController username: " + user.getEmail());
    	System.out.println("LoginController password: " + user.getPassword());  
    	AppUserDto response = appUserService.signUp(user);
    	return new ResponseEntity<LoginDTO>(user, HttpStatus.OK);
    }
    
    
}
