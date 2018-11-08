package tads.bianca.gerenciador.Model;

public class User {
    private String name;
    private String email;
    private Atividade[] atividades;

    public User() {
        this.name = null;
        this.email = null;
        this.atividades = null;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Atividade[] getAtividades() {
        return atividades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasAtividade(){
        return atividades.length > 0;
    }

    public void addAtividade(Atividade atividade){
        if (this.atividades == null){
            this.atividades = new Atividade[1];
        }
        atividade.setUser(this);
        this.atividades[this.atividades.length] = atividade;
    }

    public boolean removeAtividade(Atividade atividade){
        for (int i = 0; i < this.atividades.length; i++) {
            if (atividades[i].equals(atividade)){
                atividades[i] = null;
                atividades[i] = atividades[i + 1];
                atividades[atividades.length - 1] = null;
                return true;
            }
        }
        return false;
    }

}

