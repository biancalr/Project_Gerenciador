package tads.bianca.gerenciador.Model;

public class Localization {
    String name;
    String weather;
    String temp;
    Atividade atividade;

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
