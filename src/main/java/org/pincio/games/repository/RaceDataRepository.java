package org.pincio.games.repository;

import org.pincio.games.model.RaceData;
import org.pincio.games.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceDataRepository extends JpaRepository<RaceData, Long> {

    //public List<RaceData> findBy

}
