create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('dog', 20, '2000-01-01');
insert into fauna(name, avg_age, discovery_date) values('dopefish', 1, '2013-02-15');
insert into fauna(name, avg_age, discovery_date) values('fish', 15000, '2015-03-01');
insert into fauna(name, avg_age, discovery_date) values('cat', 15, null);
insert into fauna(name, avg_age, discovery_date) values('cow', 30, '1930-04-01');

select * from fauna
where name like '%fish%';

select * from fauna
where avg_age >= 10000 and avg_age <= 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';
