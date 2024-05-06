package com.paf.paperrex.TT.Repository;

import com.paf.paperrex.TT.Entity.Comment;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByPostAndUser(Post post, User user);


    List<Comment> findByPostPostID(Long postID);
}
