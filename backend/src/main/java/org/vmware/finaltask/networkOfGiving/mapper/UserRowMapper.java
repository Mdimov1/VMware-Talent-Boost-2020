package org.vmware.finaltask.networkOfGiving.mapper;

import org.vmware.finaltask.networkOfGiving.model.UserData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserData> {

    public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserData result = new UserData();

        result.setUsers_id(rs.getInt("Users_ID"));
        result.setUsername(rs.getString("Username"));
        result.setPassword(rs.getString("Password"));
        result.setName(rs.getString("Name"));
        result.setAge(rs.getInt("Age"));
        result.setGender(rs.getString("Gender"));
        result.setLocation(rs.getString("Location"));

        return (UserData) result;
    }
}
