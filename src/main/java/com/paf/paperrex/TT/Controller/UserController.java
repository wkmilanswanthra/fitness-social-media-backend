package com.paf.paperrex.TT.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paf.paperrex.TT.Dto.LoginDto;
import com.paf.paperrex.TT.Dto.UserDto;
import com.paf.paperrex.TT.Dto.UserResponse;
import com.paf.paperrex.TT.Entity.User;
import com.paf.paperrex.TT.Service.UserService;
import com.paf.paperrex.TT.Utils.JwtUtils;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    

    @Autowired
    private JwtUtils jwtUtils;

    

    @PostMapping("/register")
    public ResponseEntity<EntityModel<UserResponse>> register(@RequestBody UserDto entity) {
        try{
        User user = userService.save(entity);
        user.setPassword(null);
        UserResponse userResponse = new UserResponse("User registered successfully", user, null);
        EntityModel<UserResponse> response = EntityModel.of(userResponse);
        return ResponseEntity.ok(response);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(
                EntityModel.of(new UserResponse(e.getMessage(), null, null))
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<EntityModel<UserResponse>> login(@RequestBody LoginDto entity) {
        try{
        User user = userService.login(entity);
        user.setPassword(null);
        System.out.println(user);
        String token = jwtUtils.generateToken(user);
        UserResponse userResponse = new UserResponse(token, user, null);
        EntityModel<UserResponse> response = EntityModel.of(userResponse);
        return ResponseEntity.ok(response);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(
                EntityModel.of(new UserResponse(e.getMessage(), null, null))
            );
        }
    }

     @GetMapping("/me")
    public ResponseEntity<EntityModel<UserResponse>> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        System.out.println(token);
        if (token == null) {
            return ResponseEntity.badRequest().body(
                EntityModel.of(new UserResponse("Invalid token", null, null))
            );
        }
        String username = jwtUtils.extractUsername(token);
        User user = userService.findUserByUsername(username);
        UserResponse userResponse = new UserResponse(token, user, null);
        EntityModel<UserResponse> response = EntityModel.of(userResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EntityModel<UserResponse>> getUserById(@PathVariable Long userId) {
        try{
        User user = userService.findUserById(userId);
        UserResponse userResponse = new UserResponse("User found", user, null);
        EntityModel<UserResponse> response = EntityModel.of(userResponse);
        return ResponseEntity.ok(response);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(
                EntityModel.of(new UserResponse(e.getMessage(), null, null))
            );
        }
    }
    

    
    // private Class<?> getAllUsers() {
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    // }
    
    // private Class<?> getUserById(Long id) {
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    // }
    
    // private EntityModel<User> createUserLinks(User user) {
    //     EntityModel<User> resource = EntityModel.of(user);
    //     resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAllUsers()).withRel("users"));
    //     return resource;
    // }
    
}
