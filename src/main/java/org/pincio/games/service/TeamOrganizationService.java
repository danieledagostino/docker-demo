package org.pincio.games.service;

import org.pincio.games.dto.TeamDto;
import org.pincio.games.model.Team;
import org.pincio.games.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
