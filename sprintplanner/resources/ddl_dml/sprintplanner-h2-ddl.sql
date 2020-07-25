-- create schema
CREATE SCHEMA IF NOT EXISTS `sprintplanner` AUTHORIZATION root;
USE `sprintplanner`;

-- -----------------------------------------------------
-- Table `sprintplanner`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`members` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(110) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `shift` ENUM('PAR', 'BGL') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `members_email_UNIQUE` (`email` ASC));


-- -----------------------------------------------------
-- Table `sprintplanner`.`tasks`
-- Table `sprintplanner`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`tasks` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(100) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `manager_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tasks_member_id_FK`
    FOREIGN KEY (`manager_id`)
    REFERENCES `sprintplanner`.`members` (`id`));


-- -----------------------------------------------------
-- Table `sprintplanner`.`candidates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`candidates` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `priority` INT(11) NOT NULL,
  `status` ENUM('AVAILABLE', 'UNAVAILABLE', 'CURRENT') NOT NULL,
  `member_id` BIGINT(20) NOT NULL,
  `task_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `candidates_member_id_task_id_UNIQUE` (`member_id` ASC, `task_id` ASC),
  CONSTRAINT `candidates_member_id_FK`
    FOREIGN KEY (`member_id`)
    REFERENCES `sprintplanner`.`members` (`id`),
  CONSTRAINT `candidates_task_id_FK`
    FOREIGN KEY (`task_id`)
    REFERENCES `sprintplanner`.`tasks` (`id`));


-- -----------------------------------------------------
-- Table `sprintplanner`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`projects` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `github_repo` VARCHAR(100) NOT NULL,
  `github_user` VARCHAR(100) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `pi_duration` INT(11) NOT NULL,
  `sprint_duration` INT(11) NOT NULL,
  `trigram` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `projects_trigram_UNIQUE` (`trigram` ASC));

-- -----------------------------------------------------
-- Table `sprintplanner`.`releases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`releases` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pi` INT(11) NOT NULL,
  `releaser` VARCHAR(255) NULL DEFAULT NULL,
  `sprint` INT(11) NOT NULL,
  `version_number` VARCHAR(255) NULL DEFAULT NULL,
  `week` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `releases_pi_sprint_week_UNIQUE` (`pi` ASC, `sprint` ASC, `week` ASC));

-- -----------------------------------------------------
-- Table `sprintplanner`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(256) NOT NULL,
  `default_role` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `roles_code_UNIQUE` (`code` ASC));


-- -----------------------------------------------------
-- Table `sprintplanner`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` VARCHAR(1) NOT NULL,
  `account_non_locked` VARCHAR(1) NOT NULL,
  `credentials_non_expired` VARCHAR(1) NOT NULL,
  `enabled` VARCHAR(1) NOT NULL,
  `firstname` VARCHAR(256) NOT NULL,
  `lastname` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `username` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `users_username_UNIQUE` (`username` ASC));


-- -----------------------------------------------------
-- Table `sprintplanner`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`users_roles` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `users_roles_role_id_FK`
    FOREIGN KEY (`role_id`)
    REFERENCES `sprintplanner`.`roles` (`id`),
  CONSTRAINT `users_roles_user_id_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `sprintplanner`.`users` (`id`));

-- -----------------------------------------------------
-- Table `sprintplanner`.`jobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`jobs` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(10) NOT NULL,
  `task` VARCHAR(10) NOT NULL,
  `active` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`));