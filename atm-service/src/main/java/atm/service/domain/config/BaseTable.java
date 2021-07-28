package atm.service.domain.config;

public interface BaseTable {

    interface Auth {

        String ACCOUNT = "account";
    }

    interface Bank {

        String BANK_ACCOUNT = "bank_account";
        String BANK_DEPOSIT_TRANSACTION = "bank_deposit_transaction";
        String BANK_TRANSFER_TRANSACTION = "bank_transfer_transaction";
    }
}
