package org.pincio.games.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "person_team", joinColumns = {
            @JoinColumn(name = "team_id")},
            inverseJoinColumns = {
            @JoinColumn(name = "person_id")})
    private Set<Person> persons;

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

    public Set<Person> getUsers() {
        return persons;
    }

    public void setUsers(Set<Person> people) {
        this.persons = people;
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
