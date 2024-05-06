package com.paf.paperrex.TT.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.paf.paperrex.TT.Entity.User;


@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByFirstName(String firstName);
    User findUserByLastName(String lastName);
    User findUserByEmail(String email);
    User  findByUsername(String username);
    boolean existsByUsername(String username);

}