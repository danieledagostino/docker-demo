package org.pincio.games.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public class TeamDto {

    private Long teamId;

    private String teamName;

    private String type;

    private Integer typeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer partecipantNumber;

    private Integer kmDone;

    private BigDecimal points;

    private Long newJoiner;

    public TeamDto(String teamName, String type, Integer partecipantNumber) {
        this.teamName = teamName;
        this.type = type;
        this.partecipantNumber = partecipantNumber;
    }

    public TeamDto(String teamName, Integer kmDone, BigDecimal points) {
        this.teamName = teamName;
        this.kmDone = kmDone;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPartecipantNumber() {
        return partecipantNumber;
    }

    public void setPartecipantNumber(Integer partecipantNumber) {
        this.partecipantNumber = partecipantNumber;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public Integer getKmDone() {
        return kmDone;
    }

    public void setKmDone(Integer kmDone) {
        this.kmDone = kmDone;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getNewJoiner() {
        return newJoiner;
    }

    public void setNewJoiner(Long newJoiner) {
        this.newJoiner = newJoiner;
    }
}
