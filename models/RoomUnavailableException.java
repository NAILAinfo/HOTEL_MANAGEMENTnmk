package models;

public class RoomUnavailableException extends Exception {
    public RoomUnavailableException(String message) {
        super(message);
    }
}