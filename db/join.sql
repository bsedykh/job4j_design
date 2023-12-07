create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values('it');
insert into departments(name) values('administration');

insert into employees(name, department_id) values('Ivanov', 2);
insert into employees(name, department_id) values('Petrov', 2);
insert into employees(name, department_id) values('Sidorov', null);

select d.* 
from departments d
    left join employees e
        on d.id = e.department_id
where e.id is null;

select d.*, e.*
from departments d
    left join employees e
        on d.id = e.department_id;

select d.*, e.*
from employees e
    right join departments d
        on d.id = e.department_id;

select d.*, e.*
from departments d
    full join employees e
        on d.id = e.department_id;
       
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(10)
);

insert into teens(name, gender) values('Petya', 'male');
insert into teens(name, gender) values('Vasya', 'male');
insert into teens(name, gender) values('Katya', 'female');
insert into teens(name, gender) values('Olya', 'female');

select * from teens t1, teens t2
where t1.gender <> t2.gender;
