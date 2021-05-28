package org.pincio.games.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @OneToMany
    private Set<User> users;

    @OneToOne
    private RaceType type;

    @OneToMany
    private Set<RaceData> raceDatas;

    public Team(){}

    public Team(Integer teamId) {
        this.id = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public RaceType getType() {
        return type;
    }

    public void setType(RaceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RaceData> getRaceDatas() {
        return raceDatas;
    }

    public void setRaceDatas(Set<RaceData> raceDatas) {
        this.raceDatas = raceDatas;
    }
}
