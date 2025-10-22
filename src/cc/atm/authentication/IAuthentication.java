package cc.atm.authentication;

import cc.atm.CustomerAccount;
import cc.atm.bankinformationsystem.IBankInformationSystem;

public interface IAuthentication {
    /**
     * Handles user authentication and returns the authenticated account.
     * Returns null if authentication fails.
     */
    CustomerAccount authenticate(IBankInformationSystem bankInformationSystem);
}
