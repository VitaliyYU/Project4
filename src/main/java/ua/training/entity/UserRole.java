package ua.training.entity;

import java.io.Serializable;

/**
 * Created by vitaliy on 21.05.17.
 */
public class UserRole implements Serializable {
    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserRole() {

    }
}
