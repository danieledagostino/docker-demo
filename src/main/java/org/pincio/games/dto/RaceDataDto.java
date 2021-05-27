package org.pincio.games.dto;

import org.pincio.games.model.Team;

import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.util.Date;

public class RaceDataDto {

    private Date startDatetime;

    private Date endDatetime;

    private byte[] startPhotogroup;

    private byte[] endphotogroup;

    private Integer teamId;

    private Integer kmDone;

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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getKmDone() {
        return kmDone;
    }

    public void setKmDone(Integer kmDone) {
        this.kmDone = kmDone;
    }
}
