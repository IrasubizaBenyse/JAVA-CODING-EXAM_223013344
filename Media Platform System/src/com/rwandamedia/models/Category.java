package com.rwandamedia.models;

import java.util.Date;

public class Category {
    private int categoryID;
    private String name;
    private String description;
    private int createdBy;
    private Date createdAt;
    private String createdByName;
    
    public Category() {}
    
    public Category(int categoryID, String name, String description, int createdBy, Date createdAt) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    
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
        return "Category{categoryID=" + categoryID + ", name='" + name + "', description='" + description + "'}";
    }
}