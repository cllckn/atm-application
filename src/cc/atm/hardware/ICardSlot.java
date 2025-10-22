package cc.atm.hardware;

public interface ICardSlot {
    public int insertCard();

    public void ejectCard();

    public boolean isCardInserted();

    public void swallowCard();

    int verifyCard();
}
