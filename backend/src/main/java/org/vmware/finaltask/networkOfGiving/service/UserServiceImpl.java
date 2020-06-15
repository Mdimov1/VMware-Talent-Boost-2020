package org.vmware.finaltask.networkOfGiving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vmware.finaltask.networkOfGiving.model.UserData;
import org.vmware.finaltask.networkOfGiving.interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private IUserService iUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public List<UserData> getAllUsers() {
        return iUserService.getAllUsers();
    }

    @Override
    public UserData getUserByUsername(String name) {
        return iUserService.getUserByUsername(name);
    }

    @Override
    public UserData getUserByID(int id) {
        return iUserService.getUserByID(id);
    }

    @Override
    public void registerUser(String username, String password, String name, int age, String gender, String location) {
        password = passwordEncoder.encode(password);
        iUserService.registerUser(username, password, name, age, gender, location);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = getUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}