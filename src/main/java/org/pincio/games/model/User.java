package org.pincio.games.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class User {

    @Id
    @GeneratedValue(generator="PERSON_SEQ",strategy=GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String surname;
    private String password;
    private String role;
    private String token;

    @Lob
    private byte[] photoId;
    private boolean valid;

    @OneToMany
    private Set<Team> teams;

    public User() {}

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPhotoId() {
        return photoId;
    }

    public void setPhotoId(byte[] photoId) {
        this.photoId = photoId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
