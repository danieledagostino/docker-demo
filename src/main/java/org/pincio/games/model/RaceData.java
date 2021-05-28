package org.pincio.games.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RaceData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Date startDatetime;

    private Date endDatetime;

    @Lob
    private byte[] startPhotogroup;

    @Lob
    private byte[] endphotogroup;

    @OneToOne
    private Team team;

    private Integer kmDone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getStartPhotogroup() {
        return startPhotogroup;
    }

    public void setStartPhotogroup(byte[] startPhotogroup) {
        this.startPhotogroup = startPhotogroup;
    }

    public byte[] getEndphotogroup() {
        return endphotogroup;
    }

    public void setEndphotogroup(byte[] endphotogroup) {
        this.endphotogroup = endphotogroup;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getKmDone() {
        return kmDone;
    }

    public void setKmDone(Integer kmDone) {
        this.kmDone = kmDone;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }
}
