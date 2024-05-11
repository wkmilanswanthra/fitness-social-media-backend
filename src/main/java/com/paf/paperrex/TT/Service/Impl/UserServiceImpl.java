package com.paf.paperrex.TT.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paf.paperrex.TT.Dto.LoginDto;
import com.paf.paperrex.TT.Dto.UserDto;
import com.paf.paperrex.TT.Entity.User;
import com.paf.paperrex.TT.Repository.UserRepository;
import com.paf.paperrex.TT.Service.UserService;
import com.paf.paperrex.TT.Utils.PasswordEncoderUtil;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(UserDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UnsupportedOperationException("Username already exists");
        }
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new UnsupportedOperationException("Email already exists");
        }
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(PasswordEncoderUtil.hashPassword(user.getPassword()));
        return userRepository.save(newUser);
    }
    
    @Override
    public User login(LoginDto loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new UnsupportedOperationException("User not found");
        }
        if (!PasswordEncoderUtil.verifyPassword(loginDTO.getPassword(), user.getPassword())) {
            throw new UnsupportedOperationException("Invalid password");
        }
        user.setPassword(null);
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public User findUserByFirstName(String firstName) {
        throw new UnsupportedOperationException("Unimplemented method 'findUserByFirstName'");
    }

    @Override
    public User findUserByLastName(String lastName) {
        throw new UnsupportedOperationException("Unimplemented method 'findUserByLastName'");
    }

    @Override
    public User findUserByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'existsByUsername'");
    }

    @Override
    public String addNewUser(UserDto userDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'addNewUser'");
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User updateUser(Long userId, UserDto entity) {
        User user = userRepository.findById(userId).get();
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setUsername(entity.getUsername());
        return userRepository.save(user);
    }

    
    
}
