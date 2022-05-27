package com.juan.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.juan.bookclub.models.LoginUser;
import com.juan.bookclub.models.User;
import com.juan.bookclub.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userServ;
	
//	=================================== REGISTER CONTROLLERS START ==============================
	
//	RENDER LOGIN AND REGISTRATION PAGE
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
//    	RETRIEVE USER FROM SESSION
    	Long userId = (Long) session.getAttribute("user_id");
//    	CHECK IF USER ID IS NULL
    	if (userId == null) {
    		return "redirect:/";
    	} else {
//    		GO TO THE DATABASE TO RETRIEVE THE USER USING THE ID FROM SESSION
    		User thisUser = userServ.findOne(userId);
    		model.addAttribute("thisUser", thisUser);
    		return "home.jsp";
    	}
    }
    
//   POST METHOD FOR REGISTERING NEW USER
    @PostMapping("/register")
    public String createUser (@Valid @ModelAttribute("newUser")User newUser, BindingResult result, Model model, HttpSession session) {
    	userServ.register(newUser, result);
//    	CHECK FOR ERRORS IN RESULTS
    	if(result.hasErrors()) {
    		model.addAttribute("newLogin", new LoginUser());
    		return "index.jsp";
    	} else {
    		session.setAttribute("user_id", newUser.getId());
    		return "redirect:/home";
    	}
    }
    
//	=================================== REGISTER CONTROLLERS END ==============================
    
//	=================================== LOGIN CONTROLLERS START ==============================
    
    
//  LOGIN USER
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult res, Model model, HttpSession session) {
    	User user = userServ.login(newLogin, res);
    	
    	if(res.hasErrors()) {
            model.addAttribute("newUser", new User());
    		return "index.jsp";
    	} else {
    		session.setAttribute("user_id", user.getId());
    		return "redirect:/home";
    	}
    }
    
//	=================================== LOGIN CONTROLLERS END ==============================
    
    
//  LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
