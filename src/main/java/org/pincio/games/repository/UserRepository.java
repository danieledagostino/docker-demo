package org.pincio.games.repository;

import org.pincio.games.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);

    Person findByToken(String token);
}