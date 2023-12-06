create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('iphone', 1000);
insert into devices(name, price) values('macbook', 6000);
insert into people(name) values('Ivanov');
insert into people(name) values('Petrov');
insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(2, 2);

select avg(devices.price) from devices;

select
    p.id,
    p.name,
    avg(d.price)
from devices_people dp
    inner join devices d
        on dp.device_id = d.id
    inner join people p
        on dp.people_id = p.id
group by
    p.id,
    p.name
having avg(d.price) > 5000;
