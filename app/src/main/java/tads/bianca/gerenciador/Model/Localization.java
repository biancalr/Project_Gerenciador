package tads.bianca.gerenciador.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Localization implements Serializable{

    private String name;
    private transient String weather;
    private transient String temp;

    public Localization(String nome){
        this.name = nome;
        this.weather = null;
        this.temp = null;
    }

    public Localization(){
        this.name = null;
        this.weather = null;
        this.temp = null;
    }

    public String getName() {
        return name;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: " + this.name);
        sb.append("Weather: " + this.weather);
        sb.append("Temperature: " + this.temp);
        return sb.toString();
    }
}
