package fr.miniature.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int nbUsers = 0;
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

    public boolean isSubscribed(User user){
        return subscriptions.contains(user);
    }

    public void manageSubscription(User user){
        if (isSubscribed(user)){
            subscriptions.remove(user);
        }else{
            subscriptions.add(user);
        }
    }

    @Override
    public String toString() {
        return login;
    }

}
