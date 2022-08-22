package ru.job4j.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String inn;
    private Timestamp hireDate;

    @OneToMany(mappedBy = "employee")
    private List<Person> accounts = new ArrayList<>();
}
