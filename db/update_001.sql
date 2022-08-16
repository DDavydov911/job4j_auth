create table employee(
                         id serial primary key not null,
                         name varchar(255),
                         surname varchar(255),
                         inn varchar (12),
                         hire_date timestamp without time zone not null default now()
);

insert into employee (name, surname, inn) values ('petr', 'arsentev', '320700777777')

create table person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000),
                        employee_id integer references employee(id)
);

insert into person (login, password, employee_id) values ('parsentev', '123', 1);
insert into person (login, password, employee_id) values ('petr', '123', 1);
insert into person (login, password, employee_id) values ('pa', '123', 1);
