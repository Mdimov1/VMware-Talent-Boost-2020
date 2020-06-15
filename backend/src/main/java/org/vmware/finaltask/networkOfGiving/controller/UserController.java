package org.vmware.finaltask.networkOfGiving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.vmware.finaltask.networkOfGiving.service.UserServiceImpl;
import org.vmware.finaltask.networkOfGiving.model.UserData;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserData> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserData user){
        userService.registerUser(user.getUsername(), user.getPassword(), user.getName(),
                user.getAge(), user.getGender(), user.getLocation());
    }

    @GetMapping(value = "/users/{username}")
    public UserData getUser(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
}
