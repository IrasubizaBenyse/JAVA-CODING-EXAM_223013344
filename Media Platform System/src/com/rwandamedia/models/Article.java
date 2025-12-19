package com.rwandamedia.models;

import java.util.Date;

public class Article {
    private int articleID;
    private String title;
    private String description;
    private String content;
    private int categoryID;
    private double priceOrValue;
    private String status;
    private int createdBy;
    private Date createdAt;
    private String categoryName;
    private String authorName;
    
    public Article() {}
    
    // Getters and Setters
    public int getArticleID() { return articleID; }
    public void setArticleID(int articleID) { this.articleID = articleID; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    
    public double getPriceOrValue() { return priceOrValue; }
    public void setPriceOrValue(double priceOrValue) { this.priceOrValue = priceOrValue; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    
    @Override
    public String toString() {
        return "Article{articleID=" + articleID + ", title='" + title + "', status='" + status + "', category='" + categoryName + "'}";
    }
}