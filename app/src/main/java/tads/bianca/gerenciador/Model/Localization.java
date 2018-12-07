package tads.bianca.gerenciador.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Localization implements Serializable{

    private String name;
    private transient String weather;
    private transient String temp;
    private transient String latitude;
    private transient String longitude;

    public Localization(String nome){
        this.name = nome;
        this.weather = null;
        this.temp = null;
        this.latitude = null;
        this.longitude = null;
    }

    public Localization(){
        this.name = null;
        this.weather = null;
        this.temp = null;
        this.latitude = null;
        this.longitude = null;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return new StringBuilder("Name: " + this.name).toString().trim();
    }

}
