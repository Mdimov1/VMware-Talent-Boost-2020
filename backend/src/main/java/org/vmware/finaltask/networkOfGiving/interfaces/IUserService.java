package org.vmware.finaltask.networkOfGiving.interfaces;

import org.vmware.finaltask.networkOfGiving.model.UserData;

import java.util.List;

public interface IUserService {

    List<UserData> getAllUsers();

    UserData getUserByUsername(String name);

    UserData getUserByID(int id);

    void registerUser(String username, String password, String name, int age, String gender, String location);

}
