create table books (
    id serial primary key,
    name varchar(255)
);
create table authors (
    id serial primary key,
    name varchar(255)
);
create table books_authors (
    id serial primary key,
	book_id int references books(id),
	author_id int references authors(id)
);
