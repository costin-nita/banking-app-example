--liquibase formatted sql

CREATE VIEW vw_bank_transaction
  AS
  SELECT
    'd' AS transaction_type, -- deposit type
    bdt.id AS transaction_id,
    bdt.from_account_id AS owned_by_account_id,
    bdt.from_account_id,
    NULL AS from_bank_account_id,
    bdt.to_bank_account_id,
    bdt.amount,
    bdt.created_by_account,
    bdt.modified_by_account,
    bdt.date_created,
    bdt.date_modified

    FROM bank_deposit_transaction AS bdt
  UNION ALL
  SELECT
    't' AS transaction_type, -- transfer type
    btt.id AS transaction_id,
    btt.owned_by_account_id,
    NULL AS from_account_id,
    btt.from_bank_account_id,
    btt.to_bank_account_id,
    btt.amount,
    btt.created_by_account,
    btt.modified_by_account,
    btt.date_created,
    btt.date_modified

    FROM bank_transfer_transaction AS btt;

--rollback DROP VIEW vw_bank_transactions;
