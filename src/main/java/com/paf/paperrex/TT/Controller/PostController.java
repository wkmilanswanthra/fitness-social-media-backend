package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Dto.PostWithCommentsDTO;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        System.out.println(post.toString());
        if (post.getMediaType() == "images" && post.getMediaFiles().size() > 3 ) {
            throw new IllegalArgumentException("Cannot upload more than 3 images");
        } else if (post.getMediaType() == "video" && post.getMediaFiles().size() > 1) {
            throw new IllegalArgumentException("Cannot upload more than 1 video");
        }
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostsByUserId(@PathVariable Long userId) {
        List<PostWithCommentsDTO> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostWithCommentsDTO>> getAllPosts() {
        List<PostWithCommentsDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Void> commentOnPost(@PathVariable Long postId, @RequestBody String comment) {
        postService.commentOnPost(postId, comment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedia(@RequestParam("file") MultipartFile file) {
        String path = postService.uploadMedia(file);
        return ResponseEntity.ok(path);
    }
}
