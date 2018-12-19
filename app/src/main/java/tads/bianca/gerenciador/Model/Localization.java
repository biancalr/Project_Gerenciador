package tads.bianca.gerenciador.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Localization implements Parcelable{

    private String name;
    private transient String weather;
    private transient String temp;
    private double lat;
    private double lon;
    private String id;
    private String address;

    public Localization(String nome){
        this.name = nome;
        this.weather = null;
        this.temp = null;
        this.lat = 0;
        this.lon = 0;
        this.id = null;
        this.address = null;
    }

    public Localization(){
        this.name = null;
        this.weather = null;
        this.temp = null;
        this.lat = 0;
        this.lon = 0;
        this.id = null;
        this.address = null;
    }

    protected Localization(Parcel in) {
        name = in.readString();
        lat = in.readDouble();
        lon = in.readDouble();
        id = in.readString();
        address = in.readString();
    }

    public static final Creator<Localization> CREATOR = new Creator<Localization>() {
        @Override
        public Localization createFromParcel(Parcel in) {
            return new Localization(in);
        }

        @Override
        public Localization[] newArray(int size) {
            return new Localization[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp;
    }

    public Double getLat() {
        return lat;
    }
    public Double getLon() {
        return lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        StringBuffer sb = new StringBuffer("\n\n\tName: " + this.name);
        sb.append("\n\n\tAddress: " + this.address);
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(lat);
        parcel.writeDouble(lon);
        parcel.writeString(id);
        parcel.writeString(address);
    }
}
