package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.Follower;
import com.paf.paperrex.TT.Service.FollowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/followers")
public class FollowerController {

    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @PostMapping
    public ResponseEntity<Follower> addFollower(@RequestBody Follower follower) {
        try {
            Follower newFollower = followerService.addFollower(follower);
            return new ResponseEntity<>(newFollower, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{followerId}")
    public ResponseEntity<Void> removeFollower(@PathVariable Long followerId) {
        followerService.removeFollower(followerId);
        return ResponseEntity.noContent().build();
    }

}
