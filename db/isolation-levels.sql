create table reports(
    id serial primary key,
    company varchar(255),
    revenue numeric(15,2)
);

insert into reports(company, revenue) values('IBM', 100);
insert into reports(company, revenue) values('Microsoft', 200);

begin transaction isolation level serializable;
select sum(r.revenue) from reports r;
update reports set revenue = revenue + 100 where company = 'IBM';
commit;

begin transaction isolation level serializable;
select sum(r.revenue) from reports r;
update reports set revenue = revenue + 100 where company = 'Microsoft';
commit;
