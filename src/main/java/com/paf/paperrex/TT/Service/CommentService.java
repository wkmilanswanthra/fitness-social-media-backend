package com.paf.paperrex.TT.Service;

import com.paf.paperrex.TT.Entity.Comment;
import com.paf.paperrex.TT.Entity.Notification;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Entity.User;
import com.paf.paperrex.TT.Repository.CommentRepository;
import com.paf.paperrex.TT.Repository.PostRepository;
import com.paf.paperrex.TT.Repository.UserRepository;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, NotificationService notificationService) {
        
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public Comment addComment(Long postId, Long userId,Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        Comment existingComment = commentRepository.findByPostAndUser(post, user);
        if (existingComment != null) {
            throw new IllegalStateException("User " + userId + " has already commented on post " + postId);
        }

        comment.setPost(post);
        comment.setUser(user);
        comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
        commentRepository.save(comment);

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setPost(post);
        notification.setTimestamp(new Timestamp(System.currentTimeMillis()));
        notification.setNotificationType("comment");
        notification.setSeen(false);
        notificationService.createNotification(notification);

        post.setCommentsCount(post.getCommentsCount() + 1);
        postRepository.save(post);

        return comment;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found."));
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

}
