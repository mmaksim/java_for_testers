package ru.stqa.mantis.model;

public record UserData(String name,
                       String email,
                       String password,
                       String token) {

    public UserData() {
        this("", "", "", "");
    }

    public UserData withName(String name) {
        return new UserData(name, this.email, this.password, this.token);
    }

    public UserData withEmail(String email) {
        return new UserData(this.name, email, this.password, this.token);
    }

    public UserData withPassword(String password) {
        return new UserData(this.name, this.email, password, this.token);
    }

    public UserData withToken(String token) {
        return new UserData(this.name, this.email, this.password, token);
    }
}
