package io.muun.common.exception;

public class AddressFormatException extends IllegalArgumentException {

    public AddressFormatException() {
        super();
    }

    public AddressFormatException(String message) {
        super(message);
    }
}
