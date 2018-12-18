package tads.bianca.gerenciador.Model;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Atividade implements Parcelable{
    private String id;
    private String name;
    private transient Localization localization;
    private String description;
    private String date;
    private String hour;

    public Atividade() {
        this.id = null;
        this.name = null;
        this.localization = null;
        this.description = null;
        this.date = null;
        this.hour = null;
    }

    public Atividade(String name, Localization localization, @Nullable String description, String date, @Nullable String hora) {
        this.id = null;
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.date = date;
        this.hour = hora;
    }

    protected Atividade(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        date = in.readString();
        hour = in.readString();
    }

    public static final Creator<Atividade> CREATOR = new Creator<Atividade>() {
        @Override
        public Atividade createFromParcel(Parcel in) {
            return new Atividade(in);
        }

        @Override
        public Atividade[] newArray(int size) {
            return new Atividade[size];
        }
    };

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(hour);
    }
}
