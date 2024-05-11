package com.paf.paperrex.TT.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paf.paperrex.TT.Dto.LoginDto;
import com.paf.paperrex.TT.Dto.UserDto;
import com.paf.paperrex.TT.Entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User save(UserDto user);

    void deleteUser(Long id);

    User updateUser(Long id, User userDetails);

    User findUserByFirstName(String firstName);

    User findUserByLastName(String lastName);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    boolean existsByUsername(String username);

    String addNewUser(UserDto userDTO);

    User login(LoginDto loginDTO);

    User findUserById(Long userId);

    User updateUser(Long userId, UserDto entity);

    
    
}
