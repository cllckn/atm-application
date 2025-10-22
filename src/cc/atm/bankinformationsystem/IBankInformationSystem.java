package cc.atm.bankinformationsystem;

import cc.atm.CustomerAccount;

public interface IBankInformationSystem {
    public CustomerAccount verifyUser(int accountNumber, int pin);

    public void updateAccount(CustomerAccount customerAccount);
}
