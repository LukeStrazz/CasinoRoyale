package app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.deck;

/**
 * Enumeration class containing 4 card suits.
 */
public enum CardSuit {
    SPADE   ("♠"),
    CLUB    ("♣"),
    HEART   ("♥"),
    DIAMOND ("♦");

    private final String displayChar;

    CardSuit(String displayChar) {
        this.displayChar = displayChar;
    }

    @Override
    public String toString() {
        return displayChar;
    }
}
