package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.Comment;
import com.paf.paperrex.TT.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/users/{userId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @PathVariable Long userId,@RequestBody Comment comment) {
        System.out.println(comment.toString());
        Comment savedComment = commentService.addComment(postId, userId,comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(commentId, comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
