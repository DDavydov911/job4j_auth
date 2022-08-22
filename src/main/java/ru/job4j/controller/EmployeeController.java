package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";
    @Autowired
    private RestTemplate rest;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
       return employeeService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee result = employeeService.createEmployee(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/account")
    public ResponseEntity<Person> addAccount(@RequestBody Person person,
                                             @PathVariable("id") int id) {
        Employee employee = employeeService.findById(id).get();
        person.setEmployee(employee);
        Person result = rest.postForObject(API, person, Person.class);
        employee.getAccounts().add(result);
        employeeService.update(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/account")
    public ResponseEntity<Void> updateAccount(@RequestBody Person person) {
        rest.put(API_ID, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/account/aId")
    public ResponseEntity<Void> deleteAccount(@PathVariable int aId) {
        rest.delete(API_ID, aId);
        return ResponseEntity.ok().build();
    }
}

