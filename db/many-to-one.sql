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
