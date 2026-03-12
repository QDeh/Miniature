package fr.miniature.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class Post {
    static int nbPosts = 0;
    private int id;
    private User owner;
    private String content;
    private List<User> likedBy = new ArrayList<>();
    private Post parent;
    private Date date = new Date();
    private String formattedDate;

    
    public Post(User owner, String content, Post parent) {
        this.id = ++nbPosts;
        this.owner = owner;
        this.content = content;
        this.formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date) + " à " + new SimpleDateFormat("HH:mm").format(date);
    }


    public int getLikes() {
        return likedBy.size();
    }

    public void updateLikes(User user) {
        if (!likedBy.contains(user)) {
            likedBy.add(user);
        }
        else {
            likedBy.remove(user);
        }
    }


    public int getNbPosts() {
        return nbPosts;
    }


    public void setNbPosts(int nbPosts) {
        this.nbPosts = nbPosts;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }
    
    
    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getFormattedDate() {
        return formattedDate;
    }


    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }


}
