package tads.bianca.gerenciador.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private List<Atividade> atividades;

    public User() {
        this.name = null;
        this.email = null;
        this.atividades = null;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.atividades = null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasAtividade(){
        return atividades.size() > 0;
    }

    public void addAtividade(Atividade atividade){
        if (this.atividades == null){
            this.atividades = new ArrayList<>();
        }
        this.atividades.add(atividade);
    }

    public boolean removeAtividade(Atividade atividade){
        return this.atividades.remove(atividade);
    }

}

