package domain;

import com.mysql.cj.protocol.x.XMessage;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);

    }
}
