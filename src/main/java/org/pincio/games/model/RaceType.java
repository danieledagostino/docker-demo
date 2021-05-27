package org.pincio.games.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class RaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal pointsPerKm;

    public RaceType() {
    }

    public RaceType(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPointsPerKm() {
        return pointsPerKm;
    }

    public void setPointsPerKm(BigDecimal pointsPerKm) {
        this.pointsPerKm = pointsPerKm;
    }
}
