package com.theater.repository;

import com.theater.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String username);
    List<User> findAllByEmail(String username);

    List<User> findAllByPassword(String password);


    User findByActivationCode(String code);

}
