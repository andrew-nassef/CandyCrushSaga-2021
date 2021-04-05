package com.company;

public class Admin extends Users{
    public  String password;

    public Admin(){
        current_username = "admin";
        password ="admin";
    }

    public boolean checkAdminName(String user_name ){
        return user_name.equals(current_username);
    }

    public boolean checkAdminPass(String user_pass){
        return user_pass.equals(password);
    }

    @Override
    public void setCurrent_username(String name) {
        current_username = name;
    }

    @Override
    public String getCurrent_username() {
        return current_username;
    }
}