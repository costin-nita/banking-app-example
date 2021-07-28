--liquibase formatted sql

CREATE TABLE bank_account (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by_account BIGINT,
  modified_by_account BIGINT,
  date_created DATETIME NOT NULL,
  date_modified DATETIME NOT NULL,
  account_id BIGINT NOT NULL,
  amount DECIMAL(20, 5) NOT NULL,

  CONSTRAINT pk_bank_account PRIMARY KEY (id),
  CONSTRAINT fk_bank_account_account FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT fk_bank_account_account_created_by FOREIGN KEY (created_by_account) REFERENCES account (id),
  CONSTRAINT fk_bank_account_account_modified_by FOREIGN KEY (modified_by_account) REFERENCES account (id),

  INDEX ix_bank_account_account_id USING BTREE (account_id)
);

--rollback DROP TABLE IF EXISTS bank_account;

CREATE TABLE bank_deposit_transaction (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by_account BIGINT,
  modified_by_account BIGINT,
  date_created DATETIME NOT NULL,
  date_modified DATETIME NOT NULL,
  from_account_id BIGINT NOT NULL,
  to_bank_account_id BIGINT NOT NULL,
  amount DECIMAL(20, 5) NOT NULL,

  CONSTRAINT pk_bank_deposit_transaction PRIMARY KEY (id),
  CONSTRAINT fk_bank_deposit_transaction_from_account_id FOREIGN KEY (from_account_id) REFERENCES account (id),
  CONSTRAINT fk_bank_deposit_transaction_to_bank_account_id FOREIGN KEY (to_bank_account_id) REFERENCES bank_account (id),
  CONSTRAINT fk_bank_deposit_transaction_account_created_by FOREIGN KEY (created_by_account) REFERENCES account (id),
  CONSTRAINT fk_bank_deposit_transaction_account_modified_by FOREIGN KEY (modified_by_account) REFERENCES account (id),

  INDEX ix_bank_deposit_transaction_from_to USING BTREE (from_account_id, to_bank_account_id)
);

--rollback DROP TABLE IF EXISTS bank_deposit_transaction;

CREATE TABLE bank_transfer_transaction (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by_account BIGINT,
  modified_by_account BIGINT,
  date_created DATETIME NOT NULL,
  date_modified DATETIME NOT NULL,
  owned_by_account_id BIGINT NOT NULL,
  from_bank_account_id BIGINT NOT NULL,
  to_bank_account_id BIGINT NOT NULL,
  amount DECIMAL(20, 5) NOT NULL,

  CONSTRAINT pk_bank_transfer_transaction PRIMARY KEY (id),
  CONSTRAINT fk_bank_transfer_transaction_bank_account_from FOREIGN KEY (from_bank_account_id) REFERENCES bank_account (id),
  CONSTRAINT fk_bank_transfer_transaction_bank_account_to FOREIGN KEY (to_bank_account_id) REFERENCES bank_account (id),
  CONSTRAINT fk_bank_transfer_transaction_account_created_by FOREIGN KEY (created_by_account) REFERENCES account (id),
  CONSTRAINT fk_bank_transfer_transaction_account_account_modified_by FOREIGN KEY (modified_by_account) REFERENCES account (id),

  INDEX ix_bank_transfer_transaction_owned_by_from_to USING BTREE (owned_by_account_id, from_bank_account_id, to_bank_account_id),
  INDEX ix_bank_transfer_transaction_owned_by_from USING BTREE (owned_by_account_id, from_bank_account_id),
  INDEX ix_bank_transfer_transaction_owned_by_to USING BTREE (owned_by_account_id, to_bank_account_id)
);

--rollback DROP TABLE IF EXISTS bank_transfer_transaction;
