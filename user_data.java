package com.aquila.auth;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    private Map<String, String> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public boolean validate(String username, String password) {
        return users.containsKey(username) &&
               users.get(username).equals(password);
    }
}
