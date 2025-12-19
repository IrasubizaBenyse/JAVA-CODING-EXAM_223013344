package com.rwandamedia.dao;

import com.rwandamedia.system.InMemoryDatabase;
import com.rwandamedia.models.Comment;
import java.util.List;

public class CommentDAO {
    
    public List<Comment> getAllComments() {
        return InMemoryDatabase.getAllComments();
    }
    
    public boolean addComment(Comment comment) {
        System.out.println("Comment added by user: " + comment.getUserID());
        return true;
    }
    
    public boolean updateComment(Comment comment) {
        System.out.println("Comment updated: " + comment.getCommentID());
        return true;
    }
    
    public boolean deleteComment(int commentID) {
        System.out.println("Comment deleted: " + commentID);
        return true;
    }
    
    public Comment getCommentById(int commentID) {
        List<Comment> comments = getAllComments();
        for (Comment comment : comments) {
            if (comment.getCommentID() == commentID) {
                return comment;
            }
        }
        return null;
    }
}