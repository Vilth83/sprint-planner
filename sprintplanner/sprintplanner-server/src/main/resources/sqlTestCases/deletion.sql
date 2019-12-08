delete from candidate where id = 1;
delete from member where id = 1;
delete from task where id = 1;
insert into member (id, firstname, lastname, email) values (1, 'test', 'test', 'tested@member');
insert into task (id, name, description) values (1, 'release', "release object for int tests");
insert into candidate (id, status, priority, member_id, task_id) values (1, 'AVAILABLE', 0, 1,1);