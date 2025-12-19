package com.rwandamedia.models;

import java.util.Date;

public class Comment {
    private int commentID;
    private int articleID;
    private int userID;
    private String content;
    private Date createdAt;
    private String userName;
    private String articleTitle;
    
    public Comment() {}
    
    public Comment(int commentID, int articleID, int userID, String content, Date createdAt) {
        this.commentID = commentID;
        this.articleID = articleID;
        this.userID = userID;
        this.content = content;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getCommentID() { return commentID; }
    public void setCommentID(int commentID) { this.commentID = commentID; }
    
    public int getArticleID() { return articleID; }
    public void setArticleID(int articleID) { this.articleID = articleID; }
    
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getArticleTitle() { return articleTitle; }
    public void setArticleTitle(String articleTitle) { this.articleTitle = articleTitle; }
    
    @Override
    public String toString() {
        return "Comment{commentID=" + commentID + ", user='" + userName + "', content='" + content + "'}";
    }
}