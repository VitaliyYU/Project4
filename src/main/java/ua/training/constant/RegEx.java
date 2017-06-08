package ua.training.constant;

public interface RegEx {
        String NAME = "[a-zA-Zа-яА-ЯёЁїЇ'-]{1,60}";
        String USERNAME = "[a-zA-Z0-9_-]{3,16}";
        String PASSWORD = USERNAME;
}

