package com.paf.paperrex.TT.Entity;

import jakarta.persistence.*;
import com.paf.paperrex.TT.Entity.Comment;

import java.sql.Timestamp;
import java.util.List;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Long postID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "media_type")
    private String mediaType;

    @ElementCollection
    @CollectionTable(name = "media_files", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "media_file")
    private List<String> mediaFiles;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "comments_count")
    private int commentsCount;

}
