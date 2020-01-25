delete from candidate where id = 1;
delete from task where id = 1;
delete from member where id = 1;

insert into member (id, firstname, lastname, email, shift) values (1, 'test', 'test', 'tested@member', 'PAR');
insert into task (id, name, description, email, manager_id) values (1, 'release', "release object for int tests", "task@mail", 1);
insert into candidate (id, status, priority, member_id, task_id) values (1, 'AVAILABLE', 0, 1,1);