package fr.miniature.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    static int nbUsers = 0;
    private int id;
    private String login;
    private String email;
    private String password;
    private List<User> subscriptions;

    
    public User(String login, String email, String password) {
        this.id = ++nbUsers;
        this.login = login;
        this.email = email;
        this.password = password;
        this.subscriptions = new ArrayList<>();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void updateSubscriptions(User user) {
        if (!subscriptions.contains(user)) {
            subscriptions.add(user);
        } else {
            subscriptions.remove(user);
        }
    }

    @Override
    public String toString() {
        return login;
    }


}
