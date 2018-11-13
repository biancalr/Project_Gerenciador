package tads.bianca.gerenciador.Model;

import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Atividade {
    private static long id = 0;
    private User user;
    private String name;
    private Localization localization;
    private String description;
    private String date;
    private String hour;

    public Atividade() {
        id = id + 1;
        this.user = null;
        this.name = null;
        this.localization = null;
        this.description = null;
        this.date = null;
        this.hour = null;
    }

    public Atividade(String name, @Nullable Localization localization, @Nullable String description, String date, @Nullable String hora) {
        id = id + 1;
        this.user = null;
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.date = date;
        this.hour = hora;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
