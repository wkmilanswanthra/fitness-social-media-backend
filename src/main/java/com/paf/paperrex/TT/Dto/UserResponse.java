package com.paf.paperrex.TT.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import com.paf.paperrex.TT.Entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String message;
    private User user;
    private List<Link> links;
}
