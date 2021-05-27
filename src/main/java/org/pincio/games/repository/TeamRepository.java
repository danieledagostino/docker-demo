package org.pincio.games.repository;

import org.pincio.games.model.RaceType;
import org.pincio.games.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    public List<Team> findByType(RaceType type);
}
