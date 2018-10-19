package tads.bianca.gerenciador.Model;

public class User {
    private String name;
    private String email;

    public User() {
        this.name = null;
        this.email = null;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

