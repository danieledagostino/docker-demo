package org.pincio.games.service;

import org.pincio.games.dto.RaceDataDto;
import org.pincio.games.dto.TeamDto;
import org.pincio.games.model.RaceData;
import org.pincio.games.model.RaceType;
import org.pincio.games.model.Team;
import org.pincio.games.repository.RaceDataRepository;
import org.pincio.games.repository.RaceTypeRepository;
import org.pincio.games.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationService {

    @Autowired
    private RaceDataRepository raceDataRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RaceTypeRepository raceTypeRepository;

    public List<TeamDto> getLastByRace(Integer raceType) {

        List<Team> teams = teamRepository.findByType(new RaceType(raceType));

        List<TeamDto> teamDtos = teams.stream().map(t -> new TeamDto(t.getName(),
                t.getRaceDatas().stream().mapToInt(RaceData::getKmDone).sum(),
                t.getType().getPointsPerKm().multiply(
                        new BigDecimal(t.getRaceDatas().stream().mapToInt(RaceData::getKmDone).sum()))))
                .collect(Collectors.toList());

        teamDtos.sort(Comparator.comparing(TeamDto::getPoints));

        return teamDtos;
    }

    public String insertData(RaceDataDto dto) {
        RaceData raceData = new RaceData();

        raceData.setStartDatetime(dto.getStartDatetime());
        raceData.setStartPhotogroup(dto.getStartPhotogroup());

        raceData.setEndDatetime(dto.getEndDatetime());
        raceData.setEndphotogroup(dto.getEndphotogroup());

        raceData.setKmDone(dto.getKmDone());
        raceData.setTeam(new Team(dto.getTeamId()));

        try {
            raceDataRepository.save(raceData);
            return "OK";
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<RaceType> getAllRaceTypes() {

        return raceTypeRepository.findAll();
    }
}
