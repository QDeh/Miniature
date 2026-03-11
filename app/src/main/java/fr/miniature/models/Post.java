package fr.miniature.models;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Post {
    private int nbPosts = 0;
    private int id;
    private User owner;
    private String content;
    private int likes;
    private boolean isLiked;
    private Date date = new Date();
    private String formattedDate;

    
    public Post(int id, User owner, String content) {
        this.id = ++nbPosts;
        this.owner = owner;
        this.content = content;
        this.likes = 0;
        this.isLiked = false;
        this.formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date) + " à " + new SimpleDateFormat("HH:mm:ss").format(date);
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


    public int getLikes() {
        return likes;
    }


    public void setLikes(int likes) {
        this.likes = likes;
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


    public boolean isLiked() {
        return isLiked;
    }


    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
    
    public void incrementLikes(int likes){
        likes++;
    }

    public void decrementLikes(int likes){
        likes--;
    }


    public String getFormattedDate() {
        return formattedDate;
    }


    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }


}
