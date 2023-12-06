create table countries (
    id serial primary key,
    name varchar(255)
);
create table capitals (
    id serial primary key,
    name varchar(255),
	country_id int references countries(id) unique
);
