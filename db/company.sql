CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company VALUES
    (1, 'Microsoft'), 
    (2, 'IBM'), 
    (3, 'Apple'), 
    (4, 'Amazon'), 
    (5, 'Google');

INSERT INTO person VALUES
    (1, 'Ivanov Ivan', 1),
    (2, 'Petrov Petr', 2),
    (3, 'Sidorov Sidor', 5),
    (4, 'Vasilyev Vasiliy', null),
    (5, 'Sergeev Sergey', 1);

SELECT 
    p.name person,
    c.name company
FROM person p
    LEFT JOIN company c
        ON p.company_id = c.id
WHERE c.id IS DISTINCT FROM 5;

WITH person_cnt AS (
    SELECT
        c.id company_id,
        c.name company_name,
        count(p.id) cnt
    FROM company c
        LEFT JOIN person p
            ON c.id = p.company_id
    GROUP BY
        c.id,    
        c.name) 
SELECT
    pc.company_name,
    pc.cnt
FROM person_cnt pc
WHERE pc.cnt = (SELECT MAX(person_cnt.cnt) from person_cnt);
