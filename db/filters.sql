create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price numeric(15,2)
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '2023-12-06', 200);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '2023-12-10', 500);
insert into product(name, type_id, expired_date, price) values('Пломбир мороженое ', 3, '2023-12-10', 150);
insert into product(name, type_id, expired_date, price) values('Молоко экстра', 2, '2023-12-10', 500);

select * 
from product p
    join type t
        on p.type_id = t.id
where t.name = 'СЫР';

select * from product p where p.name like '%мороженое%';

select * from product p where p.expired_date < current_date;

select * from product p
where p.price = (select max(p.price) from product p);

select
    t.name,
    count (p.id)
from product p
    join type t
        on p.type_id = t.id
group by t.name;

select * 
from product p
    join type t
        on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select
    t.name
from product p
    join type t
        on p.type_id = t.id
group by t.name
having count (p.id) < 10;

select p.name, t.name 
from product p
    join type t
        on p.type_id = t.id;
