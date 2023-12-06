create table customers (
    id serial primary key,
    name varchar(255)
);
create table orders (
    id serial primary key,
	customer_id int references customers(id),
	date date,
	amount numeric(15,2)
);
select
    o.id,
    c.name customer,
    o.date,
    o.amount
from orders o join customers c
    on o.customer_id = c.id;
select
    o.id,
    o.date,
    o.amount
from orders o join customers c
    on o.customer_id = c.id
where c.name = 'Ivanov';
select distinct
    c.id,
    c.name
from customers c join orders o  
    on c.id = o.customer_id
where o.amount > 100;
