package ru.job4j.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Override
    @Query("select e from Employee e join fetch e.accounts order by e.id")
    List<Employee> findAll();

    @EntityGraph(attributePaths = {"accounts"})
//    @Query("select e from Employee e join fetch e.accounts where e.id = ?1")
    Optional<Employee> findById(int id);
}
