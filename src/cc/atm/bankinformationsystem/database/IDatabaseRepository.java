package cc.atm.bankinformationsystem.database;

import cc.atm.CustomerAccount;

public interface IDatabaseRepository {
    public CustomerAccount verifyUser(int accountNumber, int pin);

    public void updateAccount(CustomerAccount customerAccount);
}
