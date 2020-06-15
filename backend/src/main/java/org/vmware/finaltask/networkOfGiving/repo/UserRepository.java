package org.vmware.finaltask.networkOfGiving.repo;

import org.vmware.finaltask.networkOfGiving.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmware.finaltask.networkOfGiving.mapper.UserRowMapper;
import org.vmware.finaltask.networkOfGiving.interfaces.IUserService;

import java.util.List;

@Repository
public class UserRepository implements IUserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserData> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM Users", new UserRowMapper());
    }

    @Override
    public UserData getUserByUsername(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE username = ?", new UserRowMapper(), name);
    }

    @Override
    public UserData getUserByID(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE users_id = ?", new UserRowMapper(), id);
    }

    @Override
    public void registerUser(String username, String password, String name, int age, String gender, String location) {
        jdbcTemplate.update("INSERT INTO Users (Username, Password, Name, Age, Gender, Location) VALUES (?, ?, ?, ?, ?, ?)",
                username, password, name, age, gender, location);
    }
}
