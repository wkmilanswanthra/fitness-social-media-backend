package com.paf.paperrex.TT.Dto;

import java.util.List;

import com.paf.paperrex.TT.Entity.Comment;
import com.paf.paperrex.TT.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public
class PostWithCommentsDTO {
    private Post post;
    private List<Comment> comments;
    
}