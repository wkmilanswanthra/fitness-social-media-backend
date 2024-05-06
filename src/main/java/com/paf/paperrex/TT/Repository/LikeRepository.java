package com.paf.paperrex.TT.Repository;

import com.paf.paperrex.TT.Entity.Like;
import com.paf.paperrex.TT.Entity.Post;
import com.paf.paperrex.TT.Entity.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByPostAndUser(Post post, User user);
    List<Like> findByUserId(Long userId);
}
