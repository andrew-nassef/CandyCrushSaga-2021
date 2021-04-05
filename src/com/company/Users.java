/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;


public abstract class Users {
    protected String current_username;
    abstract public void setCurrent_username(String name);
    abstract public String getCurrent_username();
}