package com.epam.final_task.model.entity;


public abstract class User extends Entity{
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Role role;

    public User(int id, String login, String password, String name, String surname, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return this.id == user.id &&
                this.login.equals(user.login) &&
                this.password.equals(user.password) &&
                this.name.equals(user.name) &&
                this.surname.equals(user.surname) &&
                this.role == user.role;

    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + id;
        hash = hash * prime + (login == null ? 0 : login.hashCode());
        hash = hash * prime + (password == null ? 0 : password.hashCode());
        hash = hash * prime + (name == null ? 0 : name.hashCode());
        hash = hash * prime + (surname == null ? 0 : surname.hashCode());
        hash = hash * prime + (role == null ? 0 : role.hashCode());
        return hash*prime;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role;
    }
}
