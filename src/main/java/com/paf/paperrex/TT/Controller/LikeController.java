package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.Like;
import com.paf.paperrex.TT.Service.Impl.LikeServiceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeServiceImpl likeServiceImpl;

    public LikeController(LikeServiceImpl likeServiceImpl) {
        this.likeServiceImpl = likeServiceImpl;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> getLikes(@PathVariable Long userId) {
        try {
            List<Like> likes = likeServiceImpl.getAllLikes(userId);
            return ResponseEntity.ok(likes.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get likes: " + e.getMessage());
        }
    }

    @PostMapping("/{postId}/users/{userId}")
    public ResponseEntity<String> addLike(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            likeServiceImpl.addLike(postId, userId);
            return ResponseEntity.ok("Like added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add like: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{postId}/users/{userId}")
    public ResponseEntity<String> removeLike(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            likeServiceImpl.removeLike(postId, userId);
            return ResponseEntity.ok("Like removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove like: " + e.getMessage());
        }
    }

    @GetMapping("/{postId}/users/{userId}")
    public ResponseEntity<String> getLike(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            Like like = likeServiceImpl.getLike(postId, userId);
            return ResponseEntity.ok(like.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get like: " + e.getMessage());
        }
    }
    
}
