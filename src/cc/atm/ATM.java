/**
 * The ATM class is the module where operations are initiated.
 *
 * @author  Celal Çeken
 */
package cc.atm;

import cc.atm.authentication.*;
import cc.atm.bankinformationsystem.BankInformationSystem;
import cc.atm.bankinformationsystem.IBankInformationSystem;
import cc.atm.bankinformationsystem.database.VirtualDatabaseDriver;
import cc.atm.hardware.*;
import cc.atm.transaction.CashDeposit;
import cc.atm.transaction.CashWithdrawal;
import cc.atm.transaction.ViewBalance;

public class ATM {
    private final IScreen screen;
    private final IKeypad keypad;
    private final ICashDispenser cashDispenser;
    private final ICardSlot cardSlot;

    private static final int VIEW_BALANCE = 1;
    private static final int WITHDRAW_CASH = 2;
    private static final int DEPOSIT_CASH = 3;
    private static final int EXIT = 4;

    public ATM() {
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        cardSlot = new CardSlot();
    }

    public void start(IBankInformationSystem bankInformationSystem) {

        // 🔐 Use authentication strategy
        IAuthentication authentication =
                new CardAndPinAuthentication(screen, keypad, cardSlot);

        CustomerAccount customerAccount =
                authentication.authenticate(bankInformationSystem);

        if (customerAccount != null) {
            chooseTransaction(bankInformationSystem, customerAccount);
        } else {
            screen.displayMessage("Authentication failed. Exiting...");
            cardSlot.ejectCard();
        }
    }

    private void chooseTransaction(IBankInformationSystem bankInformationSystem, CustomerAccount customerAccount) {
        int selection;
        do {
            selection = showMainMenu();
            screen.clearScreen();
            switch (selection) {
                case VIEW_BALANCE:
                    new ViewBalance(screen, keypad, customerAccount).execute();
                    break;
                case WITHDRAW_CASH:
                    new CashWithdrawal(bankInformationSystem, screen, keypad, customerAccount, cashDispenser).execute();
                    break;
                case DEPOSIT_CASH:
                    new CashDeposit(bankInformationSystem, screen, keypad, customerAccount, cashDispenser).execute();
                    break;
                case EXIT:
                    screen.displayMessage("Exiting...");
                    cardSlot.ejectCard();
                    break;
                default:
                    screen.displayMessage("You must enter a number between 1 and 4");
            }
        } while (selection != EXIT);
    }

    private int showMainMenu() {
        screen.displayMessage("**********************************************");
        screen.displayMessage("Main Menu");
        screen.displayMessage("1 - View Balance");
        screen.displayMessage("2 - Withdraw Cash");
        screen.displayMessage("3 - Deposit Cash");
        screen.displayMessage("4 - Exit");
        screen.displayMessage("**********************************************");
        screen.displayMessage("Your Selection:");
        return keypad.getInput();
    }
}
