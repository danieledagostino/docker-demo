package org.pincio.games.repository;

import org.pincio.games.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Person, Long>, JpaRepository<Person, Long> {

    Person findByEmail(String email);

    Person findByToken(String token);

    //List<Person> findAllByRaceType();

    //@Query("select from Person p where p.teams ")
    Page<Person> findByTeamsIsEmpty(Pageable pageable);

    Person findByEmailAndPassword(String email, String password);
}