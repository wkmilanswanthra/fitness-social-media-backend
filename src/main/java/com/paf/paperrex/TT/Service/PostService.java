package com.paf.paperrex.TT.Service;
import com.paf.paperrex.TT.Entity.Comment;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Repository.CommentRepository;
import com.paf.paperrex.TT.Repository.PostRepository;
import com.paf.paperrex.TT.Dto.PostWithCommentsDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            updatedPost.setPostID(postId);
            return postRepository.save(updatedPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<PostWithCommentsDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostWithCommentsDTO> postsWithComments = new ArrayList<>();

        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPostPostID(post.getPostID());
            postsWithComments.add(new PostWithCommentsDTO(post, comments));
        }
        return postsWithComments;        
    }

    public void likePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(post -> {
            post.setLikesCount(post.getLikesCount() + 1);
            postRepository.save(post);
        });
    }

    public void commentOnPost(Long postId, String comment) {
        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(post -> {
            post.setCommentsCount(post.getCommentsCount() + 1);
            postRepository.save(post);
        });
    }

    private static final String UPLOAD_DIR = "uploads";

    public String uploadMedia(MultipartFile file) {
        try {
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            
            return filePath.toString();
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostWithCommentsDTO> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        List<PostWithCommentsDTO> postsWithComments = new ArrayList<>();

        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPostPostID(post.getPostID());
            postsWithComments.add(new PostWithCommentsDTO(post, comments));
        }
        return postsWithComments;
    }

    
}


