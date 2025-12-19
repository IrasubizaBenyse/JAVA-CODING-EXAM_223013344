package com.rwandamedia.models;

import java.util.Date;

public class Tag {
    private int tagID;
    private String name;
    private String description;
    private int createdBy;
    private Date createdAt;
    private String createdByName;
    
    public Tag() {}
    
    public Tag(int tagID, String name, String description, int createdBy, Date createdAt) {
        this.tagID = tagID;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getTagID() { return tagID; }
    public void setTagID(int tagID) { this.tagID = tagID; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }
    
    @Override
    public String toString() {
        return "Tag{tagID=" + tagID + ", name='" + name + "', description='" + description + "'}";
    }
}