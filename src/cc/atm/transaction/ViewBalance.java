package cc.atm.transaction;

import cc.atm.CustomerAccount;
import cc.atm.hardware.IKeypad;
import cc.atm.hardware.IScreen;

public class ViewBalance extends Transaction {

    public ViewBalance(IScreen screen, IKeypad keypad, CustomerAccount customerAccount) {
        super(screen, keypad, null, customerAccount);
    }

    public void execute() {
        screen.displayMessage("You selected the View Balance operation");
        screen.displayMessage(customerAccount.getCustomer().getFirstName() + " " + customerAccount.getCustomer().getLastName());
        screen.displayMessage("Your balance: " + customerAccount.getBalance());
    }
}
