delete from releases where id < 0;
delete from candidates where id < 0;
delete from tasks where id < 0;
delete from members where id < 0;
delete from projects where id < 0;


insert into members (id, firstname, lastname, email, shift) 
values 
(-1, 'first', 'test', 'first@member', 'PAR'),
(-2, 'second', 'test', 'second@member', 'BGL'),
(-3, 'third', 'test', 'third@member', 'PAR'),
(-4, 'fourth', 'test', 'fourth@member', 'BGL'),
(-5, 'fifth', 'test', 'fifth@member', 'BGL');

insert into tasks (id, name, description, email, manager_id) 
values
(-1, 'releaser', 'release object for int tests', 'task@mail', -1),
(-2, 'tester', 'tester object for int tests', 'tester@mail', -1),
(-3, 'technical', 'support object for int tests', 'support@mail', -1),
(-4, 'functional', 'support object for int tests', 'support@mail', -1),
(-5, 'notfound', 'test for not found candidate', 'support@mail', -1);

insert into candidates (id, status, priority, member_id, task_id) values 
(-1, 'AVAILABLE', 0, -1,-1),
(-2, 'CURRENT', 2, -2,-1),
(-3, 'AVAILABLE', 1, -3,-1),
(-4, 'CURRENT', 2, -3,-2),
(-5, 'AVAILABLE', 2, -1,-2),
(-6, 'CURRENT', 3, -2,-3),
(-7, 'CURRENT', 2, -3,-3),
(-8, 'CURRENT', 2, -4,-4),
(-9, 'CURRENT', 3, -1,-4),
(-10, 'AVAILABLE', 3, -5,-1);


insert into projects (id, name, pi_duration, sprint_duration, github_user, github_repo, trigram, email)
values
(-1, 'sprintplanner', 5, 2, 'Vilth83', 'sprintplanner', 'SPL', 'sprintplanner@bot');

insert into releases (pi, sprint, week, releaser)
values
(1,4,2,'dummy releaser');

INSERT INTO `users` (id, account_non_expired, firstname, lastname, password, username, account_non_locked, credentials_non_expired, enabled)
VALUES 
(1,'T','Thierry','VILLEPREUX','$2a$10$CpVc.yvqf5aoxNHy9bz.IeU67PEm2WgL18YrUOiZD/VYTW/MTp9aS','admin','T','T','T'), 
(2,'T','Thierry','VILLEPREUX','$2a$10$CpVc.yvqf5aoxNHy9bz.IeU67PEm2WgL18YrUOiZD/VYTW/MTp9aS','user','T','T','T');

INSERT INTO `roles` VALUES 
(1,'ROLE_USER','T'), 
(2,'ROLE_ADMIN','F');

INSERT INTO `users_roles` 
VALUES (1,2), (2,1);
COMMIT;
INSERT INTO `jobs` VALUES
(1, 'mail', 'releaser', 'F'),
(2, 'mail', 'support', 'F'),
(3, 'mail', 'tester', 'F'),
(4, 'roundRobin', 'releaser', 'F'),
(5, 'roundRobin', 'support', 'F'),
(6, 'roundRobin', 'tester', 'F');
