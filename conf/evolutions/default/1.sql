# --- !Ups

CREATE TABLE `user` (
  `id` BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
  ,`userID` TEXT NOT NULL
  ,`email` TEXT NOT NULL
  ,`firstName` VARCHAR(255)
  ,`lastName` VARCHAR(255)
  ,`fullName` VARCHAR(255)
--  "skills" VARCHAR(255),
--  "telephone" VARCHAR(255),
--  "ext" VARCHAR(255),
--  "mobile" VARCHAR(255),
--  "company" VARCHAR(255),
--  "jobRole" VARCHAR(255),
--  "language" VARCHAR(255),
--  "avatarURL" VARCHAR(255),
--  CONSTRAINT user_key PRIMARY KEY ("userID")
);

CREATE TABLE `company` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
  ,`name` TEXT NOT NULL
  ,`description` TEXT
  ,`logo` TEXT
--  "telephone" VARCHAR(255),
--  "ext" VARCHAR(255),
--  "mobile" VARCHAR(255),
--  "company" VARCHAR(255),
--  "jobRole" VARCHAR(255),
--  "language" VARCHAR(255),
--  "avatarURL" VARCHAR(255),
--  CONSTRAINT company_key PRIMARY KEY ("id")
);

CREATE TABLE `job_position` (
    `id` TEXT NOT NULL
    ,`title` TEXT NOT NULL
    ,`description` TEXT
    ,`company_id` TEXT
    ,`tags` TEXT
    ,`date_created` DATE
    ,`date_closed` DATE
--    CONSTRAINT jobPosition_key PRIMARY KEY ("id"),
--    CONSTRAINT company_id_fk FOREIGN KEY ("company_id") REFERENCES "company"("id")
);

# --- !Downs

DROP TABLE `user`;
DROP TABLE `company`;
DROP TABLE `job_position`;