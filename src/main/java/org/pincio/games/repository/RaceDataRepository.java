package org.pincio.games.repository;

import org.pincio.games.model.RaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceDataRepository extends JpaRepository<RaceData, Long> {

    //public List<RaceData> findBy

}
