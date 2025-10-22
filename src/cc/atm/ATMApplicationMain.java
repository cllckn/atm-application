package cc.atm;

import cc.atm.bankinformationsystem.BankInformationSystem;
import cc.atm.bankinformationsystem.IBankInformationSystem;
import cc.atm.bankinformationsystem.database.PostgreSQLDriver;
import cc.atm.bankinformationsystem.database.VirtualDatabaseDriver;

public class ATMApplicationMain {
    public static void main(String[] args) {
        IBankInformationSystem bankInformationSystem = new BankInformationSystem(new PostgreSQLDriver());
        //IBankInformationSystem bankInformationSystem = new BankInformationSystem(new VirtualDatabaseDriver());
        ATM atm = new ATM();
        atm.start(bankInformationSystem);
    }
}



