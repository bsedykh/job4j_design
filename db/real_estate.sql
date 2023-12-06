create table real_estate (
    id serial primary key, 
    name varchar(150),
    area numeric(15, 2),
    electricity boolean
);
insert into real_estate (name, area, electricity) values ('Здание', 100.5, true);
update real_estate set name = 'Строение';
delete from real_estate;
select * from real_estate;
