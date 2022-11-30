package com.tester.crs.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tester.crs.Models.User;
import com.tester.crs.Repositories.UserRepository;

@RestController
@CrossOrigin("http://localhost:4200/")
public class Ping {
    @Autowired
    UserRepository userRepository;
    
    public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    // @PostMapping("/signup")
    // public String signUp(@RequestParam(name = "fname") String fname,
    // @RequestParam(name = "lname") String lname,
    // @RequestParam(name = "uname") String username, @RequestParam(name =
    // "password") String password,
    // @RequestParam(name = "location") String location) {
    // if (userRepository.findAllByUsername(username) == null) {
    // User user = new User();
    // user.setFname(fname);
    // user.setLname(lname);
    // user.setUsername(username);
    // user.setPassword(password);
    // user.setLocation(location);
    // return "Account Created with the Username : " +
    // userRepository.save(user).getUsername();
    // }
    // return "Account with this Username Exists";
    // }

    @GetMapping("/")
    public String hello(@RequestParam(defaultValue = "") String name)
    {
        return name.trim().isEmpty() ? "Hello, World" : "Hello, "+name;
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("User") User user) {
        if(user.getUsername() == null || user.getFname().trim().isEmpty() || user.getLname().trim().isEmpty() || user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty() || user.getLocation().trim().isEmpty())
            return "Please fill the Form Completely";       
        else if(userRepository.findAllByUsername(user.getUsername()) == null)
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "Account Created with the Username "+user.getUsername();
        }   
        return "Account with Same Username Exists";
    }
}
