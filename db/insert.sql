insert into roles(name) values('admin');
insert into roles(name) values('user');

insert into rules(name) values('read users');
insert into rules(name) values('write users');
insert into rules(name) values('read items');
insert into rules(name) values('write items');

insert into roles_rules(role_id, rule_id) values(1, 1);
insert into roles_rules(role_id, rule_id) values(1, 2);
insert into roles_rules(role_id, rule_id) values(1, 3);
insert into roles_rules(role_id, rule_id) values(1, 4);
insert into roles_rules(role_id, rule_id) values(2, 1);
insert into roles_rules(role_id, rule_id) values(2, 3);

insert into users(name, role_id) values('admin', 1);
insert into users(name, role_id) values('user', 2);

insert into categories(name) values('it');
insert into categories(name) values('hr');
insert into categories(name) values('sales');

insert into states(name) values('new');
insert into states(name) values('in progress');
insert into states(name) values('done');

insert into items(name, user_id, category_id, state_id) values('reinstall windows', 2, 1, 1);
insert into comments(name, item_id) values('some comment', 1);
insert into attachs(name, item_id) values('some attachment', 1);
