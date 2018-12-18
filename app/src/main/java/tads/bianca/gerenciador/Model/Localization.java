package tads.bianca.gerenciador.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Localization implements Parcelable{

    private String name;
    private transient String weather;
    private transient String temp;
    private LatLng latLng;
    private String id;
    private String address;
    private String locale;

    public Localization(String nome){
        this.name = nome;
        this.weather = null;
        this.temp = null;
        this.latLng = null;
        this.id = null;
        this.address = null;
        this.locale = null;
    }

    public Localization(){
        this.name = null;
        this.weather = null;
        this.temp = null;
        this.latLng = null;
        this.id = null;
        this.address = null;
        this.locale = null;
    }

    protected Localization(Parcel in) {
        name = in.readString();
        latLng = in.readParcelable(LatLng.class.getClassLoader());
        id = in.readString();
        address = in.readString();
        locale = in.readString();
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

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
        return new StringBuilder("Name: " + this.name).toString().trim();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(latLng, i);
        parcel.writeString(id);
        parcel.writeString(address);
        parcel.writeString(locale);
    }
}
