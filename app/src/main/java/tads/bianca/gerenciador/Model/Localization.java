package tads.bianca.gerenciador.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Localization {

    private String name;
    private String weather;
    private String temp;
    private Atividade atividade;

    public Localization(String nome){
        this.name = nome;
        this.weather = null;
        this.temp = null;
        atividade = null;
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

    public Atividade getAtividade() {
        return atividade;
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

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: " + this.name);
        sb.append("Weather: " + this.weather);
        sb.append("Temperature: " + this.temp);
        return sb.toString();
    }
}
