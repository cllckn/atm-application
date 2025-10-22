package cc.atm.authentication;

import cc.atm.*;
import cc.atm.bankinformationsystem.IBankInformationSystem;
import cc.atm.hardware.*;

public class CardAndPinAuthentication implements IAuthentication {

    private final IScreen screen;
    private final IKeypad keypad;
    private final ICardSlot cardSlot;

    public CardAndPinAuthentication(IScreen screen, IKeypad keypad, ICardSlot cardSlot) {
        this.screen = screen;
        this.keypad = keypad;
        this.cardSlot = cardSlot;
    }

    @Override
    public CustomerAccount authenticate(IBankInformationSystem bankInformationSystem) {
        screen.displayMessage("Please insert your card...");
        Utilities.waiting();
        int accountNumber = cardSlot.insertCard();

        if (accountNumber == 0) {
            screen.displayMessage("Card not recognized.");
            cardSlot.ejectCard();
            return null;
        }

        int attempts = 0;
        CustomerAccount account = null;

        do {
            screen.displayMessage("Enter your PIN:");
            int pin = keypad.getInput();
            account = bankInformationSystem.verifyUser(accountNumber, pin);
            if (account == null) {
                screen.displayMessage("Invalid PIN. Try again.");
            }
        } while (account == null && ++attempts < 3);

        if (account == null) {
            screen.displayMessage("Too many failed attempts. Card retained.");
            return null;
        }

        screen.displayMessage("Authentication successful.");
        return account;
    }
}
