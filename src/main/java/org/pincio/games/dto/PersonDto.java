package org.pincio.games.dto;

import javax.persistence.Column;
import javax.persistence.Lob;

public class PersonDto {

    private Long id;

    private String email;
    private String name;
    private String surname;
    private byte[] photoId;

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

    public byte[] getPhotoId() {
        return photoId;
    }

    public void setPhotoId(byte[] photoId) {
        this.photoId = photoId;
    }
}
