create table reports(
    id serial primary key,
    company varchar(255),
    revenue numeric(15,2)
);

insert into reports(company, revenue) values('IBM', 100);
insert into reports(company, revenue) values('Microsoft', 200);

begin transaction;
insert into reports(company, revenue) values('Apple', 300);
savepoint sp1;
insert into reports(company, revenue) values('BMW', 400);
savepoint sp2;
insert into reports(company, revenue) values('Audi', 500);
rollback to sp2;
rollback to sp1;
commit;
