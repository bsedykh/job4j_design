CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values('Ivan', 'Ivanov', 25, 'Russia');
insert into customers(first_name, last_name, age, country) values('Petr', 'Petrov', 25, 'Russia');
insert into customers(first_name, last_name, age, country) values('Sidor', 'Sidorov', 30, 'Russia');

select
    c.first_name,
    c.last_name
from customers c
where c.age = (select min(customers.age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(100, 1);

select
    c.first_name,
    c.last_name
from customers c
where c.id not in (select o.customer_id from orders o);
