package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Query("select distinct p from Person p where p.id = ?1")
    Optional<Person> findById(int id);
}