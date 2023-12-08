create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;
call insert_data('product_1', 'producer_1', 15, 32);

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products p where p.id = d_id; 
    END;
$$;
call delete_data(1);

call insert_data('product_2', 'producer_2', 20, 50);

create or replace function f_delete_data(d_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        with deleted as (
            delete from products p
            where p.id = d_id
            returning *
        )
        select into result count(*) from deleted;
        return result;
    end;
$$;
select * from f_delete_data(2);
