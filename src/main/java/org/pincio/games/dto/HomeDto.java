package org.pincio.games.dto;

import java.util.List;

public class HomeDto {

    private PersonDto personDto;
    private List<TeamDto> teams;
    private TeamDto myTeam;

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDto> teams) {
        this.teams = teams;
    }

    public TeamDto getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(TeamDto myTeam) {
        this.myTeam = myTeam;
    }
}
