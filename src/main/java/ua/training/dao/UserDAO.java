package ua.training.dao;

import ua.training.entity.User;

import java.util.List;


public interface UserDAO {
    void create(User newUser);

    /**
     *
     * @param login user login
     * @param password secret key
     * @return user with current {@login} and {@password}
     */
    User findByLoginAndPassword(String login,String password);
    List<User> findAll();
    void update(User updateUser);
    void delete(Integer id);
}
