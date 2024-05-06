package com.paf.paperrex.TT.Service.Impl;

import com.paf.paperrex.TT.Entity.Like;
import com.paf.paperrex.TT.Entity.Notification;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Entity.User;
import com.paf.paperrex.TT.Repository.LikeRepository;
import com.paf.paperrex.TT.Repository.PostRepository;
import com.paf.paperrex.TT.Repository.UserRepository;
import com.paf.paperrex.TT.Service.NotificationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LikeServiceImpl  {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository, NotificationService notificationService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public List<Like> getAllLikes(Long userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes;
    }

    @Transactional
    public void addLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        Like existingLike = likeRepository.findByPostAndUser(post, user);
        if (existingLike != null) {
            throw new IllegalStateException("User " + userId + " has already liked post " + postId);
        }

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        like.setTimestamp(new Timestamp(System.currentTimeMillis()));

        likeRepository.save(like);

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setPost(post);
        notification.setTimestamp(new Timestamp(System.currentTimeMillis()));
        notification.setNotificationType("like");
        notification.setSeen(false);
        notificationService.createNotification(notification);

        post.setLikesCount(post.getLikesCount() + 1);
        postRepository.save(post);
    }

    @Transactional
    public void removeLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));
        Like like = likeRepository.findByPostAndUser(post, user);

        likeRepository.delete(like);
        post.setLikesCount(post.getLikesCount() - 1);
        postRepository.save(post);
    }

    public Like getLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        Like like = likeRepository.findByPostAndUser(post, user);
        if (like == null) {
            throw new IllegalArgumentException("User " + userId + " has not liked post " + postId);
        }
        return like;
    }
}
