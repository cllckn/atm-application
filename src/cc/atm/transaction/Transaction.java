package cc.atm.transaction;

import cc.atm.CustomerAccount;
import cc.atm.bankinformationsystem.IBankInformationSystem;
import cc.atm.hardware.IKeypad;
import cc.atm.hardware.IScreen;

public abstract class Transaction {

    protected final IScreen screen;
    protected final IKeypad keypad;
    protected final IBankInformationSystem bankInformationSystem;
    protected final CustomerAccount customerAccount;

    protected Transaction(IScreen screen, IKeypad keypad, IBankInformationSystem bankInformationSystem, CustomerAccount customerAccount) {
        this.screen = screen;
        this.keypad = keypad;
        this.bankInformationSystem = bankInformationSystem;
        this.customerAccount = customerAccount;
    }

    public abstract void execute();
}
