package org.vmware.finaltask.networkOfGiving.model;

public class UserData {
    private Integer users_id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String location;

    public UserData(){
    }

    public UserData(Integer users_id, String username, String password, String name, Integer age) {
        this.users_id = users_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public UserData(Integer users_id, String username, String password, String name, Integer age, String location) {
        this.users_id = users_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public UserData(Integer users_id, String username, String password, String name, Integer age, String gender, String location) {
        this.users_id = users_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUsers_id(Integer user_id) {
        this.users_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
