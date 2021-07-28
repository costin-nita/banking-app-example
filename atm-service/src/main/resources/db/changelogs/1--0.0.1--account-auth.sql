--liquibase formatted sql

CREATE TABLE account (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by_account BIGINT,
  modified_by_account BIGINT,
  date_created DATETIME NOT NULL,
  date_modified DATETIME NOT NULL,
  email NVARCHAR(100) NOT NULL,
  password NVARCHAR(300) NOT NULL,
  first_name NVARCHAR(100) NOT NULL,
  last_name NVARCHAR(100) NOT NULL,
  middle_name NVARCHAR(100),

  CONSTRAINT pk_account PRIMARY KEY (id),
  CONSTRAINT ix_account_email UNIQUE (email),
  CONSTRAINT fk_account_account_created_by FOREIGN KEY (created_by_account) REFERENCES account (id),
  CONSTRAINT fk_account_account_modified_by FOREIGN KEY (modified_by_account) REFERENCES account (id)
);

--rollback DROP TABLE IF EXISTS account;
