package com.rwandamedia.dao;

import com.rwandamedia.system.DatabaseConnection;
import com.rwandamedia.models.Tag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {
    
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT t.*, u.FullName as CreatedByName FROM Tags t " +
                    "LEFT JOIN Users u ON t.CreatedBy = u.UserID " +
                    "ORDER BY t.Name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setTagID(rs.getInt("TagID"));
                tag.setName(rs.getString("Name"));
                tag.setDescription(rs.getString("Description"));
                tag.setCreatedBy(rs.getInt("CreatedBy"));
                tag.setCreatedAt(rs.getTimestamp("CreatedAt"));
                tag.setCreatedByName(rs.getString("CreatedByName"));
                
                tags.add(tag);
            }
        } catch (SQLException e) {
            System.err.println("Error getting tags: " + e.getMessage());
            e.printStackTrace();
        }
        return tags;
    }
    
    public boolean addTag(Tag tag) {
        String sql = "INSERT INTO Tags (Name, Description, CreatedBy) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getDescription());
            stmt.setInt(3, tag.getCreatedBy());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding tag: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateTag(Tag tag) {
        String sql = "UPDATE Tags SET Name = ?, Description = ? WHERE TagID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getDescription());
            stmt.setInt(3, tag.getTagID());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating tag: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteTag(int tagID) {
        String sql = "DELETE FROM Tags WHERE TagID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tagID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting tag: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public Tag getTagById(int tagID) {
        String sql = "SELECT t.*, u.FullName as CreatedByName FROM Tags t " +
                    "LEFT JOIN Users u ON t.CreatedBy = u.UserID " +
                    "WHERE t.TagID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tagID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Tag tag = new Tag();
                tag.setTagID(rs.getInt("TagID"));
                tag.setName(rs.getString("Name"));
                tag.setDescription(rs.getString("Description"));
                tag.setCreatedBy(rs.getInt("CreatedBy"));
                tag.setCreatedAt(rs.getTimestamp("CreatedAt"));
                tag.setCreatedByName(rs.getString("CreatedByName"));
                
                return tag;
            }
        } catch (SQLException e) {
            System.err.println("Error getting tag by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}