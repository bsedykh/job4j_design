create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('sedan');
insert into car_bodies(name) values('hatchback');
insert into car_bodies(name) values('pickup');

insert into car_engines(name) values('V6 2400');
insert into car_engines(name) values('V8 3600');

insert into car_transmissions(name) values('automatic 6-gear');
insert into car_transmissions(name) values('manual 5-gear');

insert into cars(name, body_id, engine_id, transmission_id) values('audi', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('volvo', 2, null, null);


select
    c.id,
    c.name car_name,
    cb.name body_name,
    ce.name engine_name,
    ct.name transmission_name
from cars c
    left join car_bodies cb
        on c.body_id = cb.id
    left join car_engines ce
        on c.engine_id = ce.id
    left join car_transmissions ct
        on c.transmission_id = ct.id;

select
    cb.name
from car_bodies cb
    left join cars c
        on cb.id = c.body_id
where c.id is null;

select
    ce.name
from car_engines ce
    left join cars c
        on ce.id = c.engine_id
where c.id is null;

select
    ct.name
from car_transmissions ct
    left join cars c
        on ct.id = c.transmission_id
where c.id is null;
