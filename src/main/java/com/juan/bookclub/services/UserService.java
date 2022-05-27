package com.juan.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.juan.bookclub.models.LoginUser;
import com.juan.bookclub.models.User;
import com.juan.bookclub.repositories.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    public User register(User newUser, BindingResult result) {
//    	REJECT IF THE EMAIL IS TAKEN (PRESENT IN THE DB ALREADY)
        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
        if(potentialUser.isPresent()) {
        	result.rejectValue("email", "register_errors_unicorns", "This email is taken");
        }
//        REJECT IF PASSWORD DOES NOT MATCH WITH CONFIRM PASSWORD
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "register_errors_unicorns", "This confirm password must match password");
        }
        if(result.hasErrors()) {
        	return null;
        } else {
//        	HASH AND SET PASSWORD. SAVE USER TO THE DATABASE.
        	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        	newUser.setPassword(hashed);
//        	NOW WE CAN USER TO THE DATABASE
        	return userRepo.save(newUser);
        	
        }
    }
    
    
    public User login(LoginUser newLoginObject, BindingResult result) {
//        FIND THE USER IN THE DATABASE
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if(!potentialUser.isPresent()) {
    		result.rejectValue ("email", "login_error", "Email not found.");
    	} else {    		
    		User user = potentialUser.get();
    		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
//    		IF PASSWORD DOES NOT MATCH REJECT 
    			result.rejectValue("password", "login_error", "invalid password");
    		}
//    	REJECT USER IF BCRYPT PASSWORD MATCH FAILS
//    	RETURN NULL IF RESULT HAS ERRORS
    		if(result.hasErrors()) {
    			return null;
    		} else {
//    		RETURN USER OBJECT
    			return user;
    		}
    	}
    	
    	return null;
    	
    }
    
//    FIND ONE
    public User findOne(Long id) {
    	Optional<User> potentialUser=userRepo.findById(id);
    	if (potentialUser.isPresent()) {
    		return potentialUser.get();
    	} else {
    		return null;
    	}
    }
}
