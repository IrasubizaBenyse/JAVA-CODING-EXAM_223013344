package com.rwandamedia.models;

import java.util.Date;

public class Advertisement {
    private int advertisementID;
    private String title;
    private String description;
    private String imageURL;
    private String targetURL;
    private String status;
    private int createdBy;
    private Date createdAt;
    private String createdByName;
    
    public Advertisement() {}
    
    public Advertisement(int advertisementID, String title, String description, String imageURL, 
                        String targetURL, String status, int createdBy, Date createdAt) {
        this.advertisementID = advertisementID;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.targetURL = targetURL;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getAdvertisementID() { return advertisementID; }
    public void setAdvertisementID(int advertisementID) { this.advertisementID = advertisementID; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
    
    public String getTargetURL() { return targetURL; }
    public void setTargetURL(String targetURL) { this.targetURL = targetURL; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }
    
    @Override
    public String toString() {
        return "Advertisement{advertisementID=" + advertisementID + ", title='" + title + "', status='" + status + "'}";
    }
}