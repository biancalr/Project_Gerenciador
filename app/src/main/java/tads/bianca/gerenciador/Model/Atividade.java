package tads.bianca.gerenciador.Model;

import android.support.annotation.Nullable;

import java.util.Date;

public class Atividade {
    private User user;
    private String name;
    private String localization;
    private String description;
    private String date;
    private String hour;

    public Atividade() {
//        this.user = null;
        this.name = null;
        this.localization = null;
        this.description = null;
        this.date = null;
        this.hour = null;
    }

    public Atividade(String name, @Nullable String localization, @Nullable String description, String date, @Nullable String hora) {
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.date = date;
        this.hour = hora;
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

    public String getLocalization() {
        return localization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
