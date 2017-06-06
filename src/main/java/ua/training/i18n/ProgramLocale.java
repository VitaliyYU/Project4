package ua.training.i18n;

import java.util.Locale;


public enum ProgramLocale {

    EN(new Locale("en", "EN")),
    RU(new Locale("ru", "RU")),
    UA(new Locale("ua", "UA"));

    public static final ProgramLocale DEFAULT_LOCALE = EN;

    private Locale locale;

    ProgramLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String toString() {
        return locale.toString();
    }

    public String getLanguage() {
        return locale.getLanguage();
    }
}
