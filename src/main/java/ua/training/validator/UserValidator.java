package ua.training.validator;

import ua.training.constant.RegEx;
import ua.training.entity.User;

public class UserValidator {

    public boolean isValidName(String name) {
        if (name==null) return false;
        if (!name.matches(RegEx.NAME)) {
                return false;
            }
            return true;

    }

    public boolean isValidPassword(String password) {
        if (password==null) return false;
        if (!password.matches(RegEx.PASSWORD)) {

            return false;
        }
        return true;
    }

    public boolean isValidLogin(String username) {
        if (username==null) return false;
        if (!username.matches(RegEx.USERNAME)){

            return false;
        }
        return true;
    }

    public boolean isValid(User entity) {
        return isValidName(entity.getName())
                & isValidName(entity.getSurname())
                & isValidPassword(entity.getPassword())
                & isValidLogin(entity.getLogin());
    }
}
