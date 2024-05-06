package com.paf.paperrex.TT.Service;

import org.springframework.stereotype.Service;

import com.paf.paperrex.TT.Entity.Follower;
import com.paf.paperrex.TT.Repository.FollowerRepository;

@Service
public class FollowerService {

    private final FollowerRepository followerRepository;

    public FollowerService(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    public Follower addFollower(Follower follower) {
        return followerRepository.save(follower);
    }

    public void removeFollower(Long id) {
        followerRepository.deleteById(id);
    }

}