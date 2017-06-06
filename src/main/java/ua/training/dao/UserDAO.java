package ua.training.dao;

import ua.training.entity.User;

import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface UserDAO {
    void create(User newUser);
    User findById(Integer id);
    User findByLogin(String login);
    User findByLoginAndPassword(String login,String password);
    List<User> findAll();
    void update(User updateUser);
    void delete(Integer id);
}
