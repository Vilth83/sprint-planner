delete from releases where id < 0;
delete from candidates where id < 0;
delete from tasks where id < 0;
delete from members where id < 0;
delete from projects where id < 0;


insert into members (id, firstname, lastname, email, shift) 
values 
(-1, 'first', 'test', 'first@member', 'PAR'),
(-2, 'second', 'test', 'second@member', 'BGL'),
(-3, 'third', 'test', 'third@member', 'PAR');

insert into tasks (id, name, description, email, manager_id) 
values
(-1, 'releaser', 'release object for int tests', 'task@mail', -1),
(-2, 'tester', 'tester object for int tests', 'tester@mail', -1),
(-3, 'support', 'support object for int tests', 'support@mail', -1),
(-4, 'other', 'support object for int tests', 'support@mail', -1);


insert into candidates (id, status, priority, member_id, task_id) values 
(-1, 'AVAILABLE', 0, -1,-1),
(-2, 'CURRENT', 2, -2,-1),
(-8, 'AVAILABLE', 1, -3,-1),
(-3, 'CURRENT', 2, -3,-2),
(-4, 'CURRENT', 2, -1,-2),
(-5, 'CURRENT', 3, -2,-3),
(-6, 'AVAILABLE', 2, -3,-3),
(-7, 'CURRENT', 3, -1,-3);


insert into projects (id, name, pi_duration, sprint_duration, github_user, github_repo, trigram, email)
values
(-1, 'sprintplanner', 5, 2, 'Vilth83', 'sprintplanner', 'SPL', 'sprintplanner@bot');

insert into releases (pi, sprint, week, releaser)
values
(1,4,2,'dummy releaser');
