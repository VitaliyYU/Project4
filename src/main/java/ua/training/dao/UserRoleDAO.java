package ua.training.dao;

import ua.training.entity.UserRole;

import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface UserRoleDAO {
    void create(UserRole newRole);
    UserRole findById(Integer id);
    List<UserRole> findAll();
    void update(UserRole updateRole);
    void delete(Integer id);
}
