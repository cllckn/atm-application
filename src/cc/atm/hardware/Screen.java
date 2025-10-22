package cc.atm.hardware;

public class Screen implements IScreen {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void clearScreen() {
    }
}
