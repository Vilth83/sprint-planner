USE `sprintplanner`;

insert into projects(name, email, github_user, github_repo, pi_duration, sprint_duration, trigram) values 
('sprintplanner', 'sprintplanner@bot','vilth83','sprint-planner', 3, 3, 'SPL');
COMMIT;
insert into members (email, firstname, lastname, shift) values 
('frank@marshall', 'Frank', 'Marshall', 'BGL'),
('thierry@villepreux', 'Thierry', 'Villepreux', 'BGL'),
('ioana@ardelean', 'Ioana', 'Ardelean', 'BGL'),
('tantely@andria', 'Tantely', 'Andria', 'BGL'),
('nathalie@robert', 'Nathalie', 'Robert', 'BGL'),
('jonathan@luminuku', 'Jonathan', 'Luminuku', 'BGL'),
('david@dik', 'David', 'Dik', 'BGL'),
('remy@guilloux', 'Remy', 'Guilloux', 'PAR'),
('anneso@jehu', 'Anne-Sophie', 'Jehu', 'PAR'),
('philippe@amice', 'Philippe', 'Amice', 'PAR'),
('louis@godlewski', 'Louis', 'Godlewski', 'PAR'),
('patricia@leclerc', 'Patricia', 'Leclerc', 'PAR'),
('lea@limelette', 'Lea', 'Limelette', 'PAR');
COMMIT;
insert into releases (pi, sprint, week, version_number, releaser)
values (1,1,0,'v0', 'start'),
(1,1,1,'v1.1.1', 'bob'),
(1,1,2,'v1.1.2', 'louis'),
(1,1,3,'v1.1.3', 'lea'),
(1,2,1,'v1.2.1', 'frank');
COMMIT;
insert into tasks (description, email, name, manager_id) values
('manage releases', 'releaser@mail', 'releaser', 1),
('manage support', 'support@mail', 'technical', 1),
('handle tests', 'test@mail', 'tester', 1),
('manage support', 'support@mail', 'functional', 1);
COMMIT;
insert into candidates (priority, `status`, member_id, task_id) values 
(2, 'CURRENT', 1, 1),
(1, 'AVAILABLE', 2, 1),
(0, 'AVAILABLE', 3, 1),
(1, 'CURRENT', 4, 2),
(0, 'AVAILABLE', 5, 2),
(1, 'CURRENT', 6, 2),
(0, 'AVAILABLE', 7, 2),
(0, 'AVAILABLE', 8, 3),
(1, 'CURRENT', 9, 3),
(1, 'CURRENT', 10, 4),
(0, 'AVAILABLE', 11, 4),
(1, 'CURRENT', 12, 4),
(0, 'AVAILABLE', 13, 4);
COMMIT;
INSERT INTO `users` (id, account_non_expired, firstname, lastname, password, username, account_non_locked, credentials_non_expired, enabled)
VALUES 
(1,'T','Thierry','VILLEPREUX','$2a$10$CpVc.yvqf5aoxNHy9bz.IeU67PEm2WgL18YrUOiZD/VYTW/MTp9aS','admin','T','T','T'), 
(2,'T','Thierry','VILLEPREUX','$2a$10$CpVc.yvqf5aoxNHy9bz.IeU67PEm2WgL18YrUOiZD/VYTW/MTp9aS','user','T','T','T');

COMMIT;
INSERT INTO `roles` VALUES 
(1,'ROLE_USER','T'), 
(2,'ROLE_ADMIN','F');
COMMIT;
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
COMMIT;
