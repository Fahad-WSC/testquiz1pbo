package pbo.models;

public class MarketException extends Exception {
    public MarketException(String message) {
        super("Market Exception: " + message);
    }
}