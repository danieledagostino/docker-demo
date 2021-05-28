package org.pincio.games.service;

import org.pincio.games.dto.TeamDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.model.RaceType;
import org.pincio.games.model.Team;
import org.pincio.games.model.User;
import org.pincio.games.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamOrganizationService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamDto> loadAllTeams() {

        List<Team> teams = teamRepository.findAll();

       return teams.stream().map( t -> new TeamDto(
                    t.getName(),
                    t.getType().getName(),
                    t.getUsers().size())).collect(Collectors.toList());
    }

    public List<TeamDto> loadAllTeamsByRaceType(Long raceTypeId) {

        List<Team> teams = teamRepository.findByType(new RaceType(raceTypeId));

        return teams.stream().map( t -> new TeamDto(
                t.getName(),
                t.getType().getName(),
                t.getUsers().size())).collect(Collectors.toList());
    }

    public String joinTeam(TeamDto dto) {

        Team team = teamRepository.findById(dto.getTeamId()).get();
        team.getUsers().add(new User(dto.getNewJoiner()));

        teamRepository.save(team);

        return "OK";
    }
}
