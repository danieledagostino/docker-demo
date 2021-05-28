
package org.pincio.games.repository;

import org.pincio.games.model.RaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceTypeRepository extends JpaRepository<RaceType, Long> {


}

