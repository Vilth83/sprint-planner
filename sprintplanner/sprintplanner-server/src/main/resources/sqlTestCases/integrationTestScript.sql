delete from release_version where id < 0;
delete from candidate where id < 0;
delete from task where id < 0;
delete from member where id < 0;
delete from project where id < 0;


insert into member (id, firstname, lastname, email, shift) 
values 
(-1, 'first', 'test', 'first@member', 'PAR'),
(-2, 'second', 'test', 'second@member', 'BGL'),
(-3, 'third', 'test', 'third@member', 'PAR');

insert into task (id, name, description, email, manager_id) 
values
(-1, 'releaser', "release object for int tests", "task@mail", -1),
(-2, 'tester', "tester object for int tests", "tester@mail", -1);

insert into candidate (id, status, priority, member_id, task_id) values 
(-1, 'AVAILABLE', 0, -1,-1),
(-2, 'AVAILABLE', 1, -2,-1),
(-3, 'CURRENT', 2, -3,-2);

insert into project (id, name, pi_duration, sprint_duration, github_user, github_repo)
values
(-1, "sprintplanner", 5, 2, "Vilth83", "sprintplanner");

insert into release_version (pi, sprint, week, assignee_id)
values
(1,4,2,-1),
(1,5,1,-2),
(1,5,2,-3);
