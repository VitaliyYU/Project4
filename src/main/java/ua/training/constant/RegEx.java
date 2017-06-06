package ua.training.constant;

/**
 * Created by vitaliy on 03.06.17.
 */
public class RegEx {
        public static final String NAME = "[a-zA-Zа-яА-ЯёЁїЇ'-]{1,60}";
        public static final String USERNAME = "[a-zA-Z0-9_-]{3,16}";
        public static final String PASSWORD = USERNAME;
}

