-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sprintplanner
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sprintplanner
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sprintplanner` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sprintplanner` ;

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
  UNIQUE INDEX `members_email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sprintplanner`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`tasks` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(100) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `manager_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tasks_member_id_IDX` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `tasks_member_id_FK`
    FOREIGN KEY (`manager_id`)
    REFERENCES `sprintplanner`.`members` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  UNIQUE INDEX `candidates_member_id_task_id_UNIQUE` (`member_id` ASC, `task_id` ASC) VISIBLE,
  INDEX `candidates_member_id_IDX` (`member_id` ASC) VISIBLE,
  INDEX `candidates_task_id_IDX` (`task_id` ASC) VISIBLE,
  CONSTRAINT `candidates_member_id_FK`
    FOREIGN KEY (`member_id`)
    REFERENCES `sprintplanner`.`members` (`id`),
  CONSTRAINT `candidates_task_id_FK`
    FOREIGN KEY (`task_id`)
    REFERENCES `sprintplanner`.`tasks` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  UNIQUE INDEX `projects_trigram_UNIQUE` (`trigram` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  UNIQUE INDEX `releases_pi_sprint_week_UNIQUE` (`pi` ASC, `sprint` ASC, `week` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `sprintplanner`.`roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(256) NOT NULL,
  `default_role` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `roles_code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  UNIQUE INDEX `users_username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sprintplanner`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`users_roles` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `users_roles_user_id_IDX` (`user_id` ASC) VISIBLE,
  INDEX `users_roles_role_id_IDX` (`role_id` ASC) VISIBLE,
  CONSTRAINT `users_roles_role_id_FK`
    FOREIGN KEY (`role_id`)
    REFERENCES `sprintplanner`.`roles` (`id`),
  CONSTRAINT `users_roles_user_id_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `sprintplanner`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sprintplanner`.`jobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprintplanner`.`jobs` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(10) NOT NULL,
  `task` VARCHAR(10) NOT NULL,
  `active` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;